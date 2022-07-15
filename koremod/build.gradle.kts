plugins {
    kotlin("js") version "1.7.10"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(npm("esbuild", "0.14.49"))
}

kotlin {
    js {
        nodejs()
    }
}

tasks.named<org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile>("compileKotlinJs").configure {
    kotlinOptions.moduleKind = "commonjs"
}

tasks.register<Exec>("esbuild") {
    commandLine("node", "build.js")

    environment("NODE_PATH", rootProject.buildDir.resolve("js/node_modules").absolutePath)

    dependsOn(tasks.named("compileKotlinJs"))
}
