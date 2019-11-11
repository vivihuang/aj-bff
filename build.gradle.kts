import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        jcenter()
    }
}

plugins {
    id("org.springframework.boot") version "2.2.0.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    id("io.gitlab.arturbosch.detekt").version("1.1.1")

    kotlin("jvm") version "1.3.50"
    kotlin("plugin.spring") version "1.3.50"

    idea
}

group = "com.askjamie"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
        exclude(group = "org.mockito")
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation("com.ninja-squad:springmockk:1.1.3")
    testImplementation("org.assertj:assertj-core:3.14.0")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:Hoxton.RC1")
    }
}

/**
 * configure plugin and tasks
 */
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt> {
    // include("**/special/package/**") // only analyze a sub package inside src/main/kotlin
    // exclude("**/special/package/internal/**") // but exclude our legacy internal package
}

detekt {
    toolVersion = "1.1.1"
    input = files("src/main/kotlin")
}

val sourceSets = the<SourceSetContainer>()

sourceSets {
    create("apiTest") {
        java.srcDir("src/apiTest/kotlin")
        resources.srcDir("src/apiTest/resources")
        compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
        runtimeClasspath += output + compileClasspath
    }
}

tasks.register<Test>("apiTest") {
    description = "Runs the api tests."
    group = "verification"
    testClassesDirs = sourceSets["apiTest"].output.classesDirs
    classpath = sourceSets["apiTest"].runtimeClasspath
    mustRunAfter(tasks["test"])
}

tasks.named("check") {
    dependsOn("apiTest")
}

idea {
    module {
        outputDir = file("$buildDir/classes/main")
        testOutputDir = file("$buildDir/classes/test")
        jdkName = "1.8"
    }
}
