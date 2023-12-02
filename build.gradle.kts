plugins {
    kotlin("jvm") version "1.9.0"
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}