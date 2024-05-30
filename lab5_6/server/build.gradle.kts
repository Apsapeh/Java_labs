plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}



//java {
//    toolchain {
//        languageVersion.set(JavaLanguageVersion.of(17))
//    }
//    dependencies {
//        implementation(files("deps/json-20240303.jar"))
//    }
//}

//tasks.getByName("run", JavaExec::class) {
//    standardInput = System.`in`
//}
//
//tasks.javadoc {
//    destinationDir = file("${buildDir}/docs/javadoc")
//}

java {
//    toolchain {
//        languageVersion.set(JavaLanguageVersion.of(17))
//    }
//    dependencies {
//        implementation(files("deps/json-20240303.jar"))
//    }
}

application {
    mainClass = "server.Main"
}

dependencies {
    implementation(project(":general"))
    implementation(files("$rootDir/deps/json-20240303.jar"))
}


/*asks.jar {
    archiveBaseName.set("server")
    manifest {
        attributes(
                mapOf(
                        "Main-Class" to application.mainClass,
                        "Class-Path" to "json-20240303.jar"
                )
        )
    }

}*/

tasks.jar {
    archiveBaseName.set("server")
    manifest {
        attributes(
                mapOf(
                        "Main-Class" to application.mainClass,
                        "Class-Path" to "json-20240303.jar"
                )
        )
    }
}