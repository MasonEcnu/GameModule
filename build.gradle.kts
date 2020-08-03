import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    application
    kotlin("jvm") version "1.3.72"
}

group = "com.mason"
version = "1.0-SNAPSHOT"

application {
    mainClassName = "com.mason"
}

repositories {
    maven {
        url = uri("https://maven.aliyun.com/repository/public")
    }
//        mavenCentral()
}

dependencies {
    implementation("com.alibaba:fastjson:1.2.68")
    implementation("com.google.guava:guava:28.1-jre")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

registerGenerateTargetJarTask()

tasks.jar {
    val packagePath = "com\\mason\\practice\\file"
    val fullPackageName = "com.mason.practice.file"
    val patchClass = "MusicCopyToolKt"

    from(packagePath) {
        configurations.runtime.map { zipTree(it) }
    }

    manifest {
        attributes("Main-Class" to "$fullPackageName.$patchClass")
    }
}