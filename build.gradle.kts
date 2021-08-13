plugins {
    kotlin("jvm") version "1.5.21"
}

repositories {
    mavenCentral() // 코드 한줄을 줄여주셨습니다 각별님 감사합니다
    maven("https://papermc.io/repo/repository/maven-public/") // PaperMC
}

dependencies {
    compileOnly(kotlin("stdlib")) // Kotlin
    compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT") // Paper Latest
    compileOnly("io.github.monun:tap-api:4.1.1")
    compileOnly("io.github.monun:kommand-api:2.2.0") // 와 shadowJar 버리는 시대가 올 줄이야
}

tasks { // 아니 시발 페이퍼 개놈들이 굳이 꼭 16만 되게 해야할 이유가 뭔데요
    processResources {
        filesMatching("**/*.yml") {
            expand(project.properties)
        }
        filteringCharset = "UTF-8"
    }
    jar {
        archiveClassifier.set("dist")
        archiveVersion.set("")
    }
    create<Copy>("dist") {
        from (jar)
        into(".\\.server\\plugins")
    }
}
