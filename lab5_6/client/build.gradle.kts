plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}





//tasks.getByName("run", JavaExec::class) {
//    standardInput = System.`in`
//}
//
//tasks.javadoc {
//    destinationDir = file("${buildDir}/docs/javadoc")
//}


application {
    mainClass = "client.Main"
}

dependencies {
    implementation(project(":general"))
}


tasks.jar {
    archiveBaseName.set("client")
    manifest {
        attributes(
                mapOf(
                        "Main-Class" to application.mainClass,
                        "Class-Path" to "json-20240303.jar"
                )
        )
    }

}
