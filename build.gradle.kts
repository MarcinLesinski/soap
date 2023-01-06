import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.13.RELEASE"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
    //cxfCodegen.wsdl2java
    id("io.mateo.cxf-codegen") version "1.0.3"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {

    implementation("com.sun.xml.ws:rt:2.3.1")
    implementation("javax.xml.ws:jaxws-api:2.2")
    cxfCodegen("javax.xml.ws:jaxws-api:2.2")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

cxfCodegen {
    wsdl2java {
        create("Sample") {
            wsdl.set(file("$projectDir/src/main/resources/wsdl/CountryInfoService.wsdl"))
            outputDir.set(file("${project.buildDir}/generated/wsdl"))
        }
    }
}

tasks.compileJava {
    dependsOn(tasks.wsdl2java)
}

tasks.compileKotlin {
    dependsOn(tasks.wsdl2java)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
