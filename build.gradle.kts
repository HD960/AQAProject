plugins {
    id("java")
    id("io.qameta.allure") version "3.0.1"
}

group = "com.dhm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.codeborne:selenide:7.16.0")
    implementation("io.qameta.allure:allure-selenide:2.29.1")
    implementation("io.qameta.allure:allure-rest-assured:2.29.1")
    testImplementation("io.rest-assured:rest-assured:5.5.6")
    testImplementation("org.projectlombok:lombok:1.18.42")
    testCompileOnly ("org.projectlombok:lombok:1.18.42")
    testAnnotationProcessor ("org.projectlombok:lombok:1.18.42")
    testImplementation("com.google.code.gson:gson:2.13.1")
    implementation("org.yaml:snakeyaml:2.5")
    testImplementation("org.yaml:snakeyaml:2.5")

}

tasks.test {
    useJUnitPlatform()
}