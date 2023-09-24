import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.12"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("com.google.cloud.tools.jib") version "3.3.2"
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
    id("jacoco")
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
}

group = "com.miintto"
version = "0.0.1-SNAPSHOT-${System.currentTimeMillis()}"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("co.elastic.logging:logback-ecs-encoder:1.5.0")
    implementation("com.google.code.gson:gson")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("org.postgresql:postgresql:42.5.4")
    implementation("org.redisson:redisson-spring-boot-starter:3.23.5")

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.21")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.testcontainers:junit-jupiter:1.17.6")
    testImplementation("org.testcontainers:postgresql:1.17.6")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:1.17.6")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

jib {
    from {
        image = "adoptopenjdk/openjdk11"
    }
    to {
        image = "matstagram"
    }
    container {
        creationTime.set("USE_CURRENT_TIMESTAMP")
    }
}

jacoco {
    toolVersion = "0.8.10"
}

tasks.jacocoTestReport {
    reports {
        html.isEnabled = true
        html.destination = file("$buildDir/jacoco/index.html")
    }
}
