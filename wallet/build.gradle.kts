import extensions.implementation
import extensions.api

group = ApplicationConfiguration.groupId
version = ApplicationConfiguration.version

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    java
    id("java-library")
    id("maven-publish")
    id("signing")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

dependencies {
    api(ApplicationDependencies.dependencies)

    // testing
    testImplementation(ApplicationDependencies.junit)
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = ApplicationConfiguration.artifactId
            from(components["java"])
            artifact(sourcesJar.get())
            artifact(javadocJar.get())
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set(ApplicationConfiguration.name)
                description.set(ApplicationConfiguration.description)
                url.set(ApplicationConfiguration.url)
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("http://www.opensource.org/licenses/mit-license.php")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/paysera/${ApplicationConfiguration.artifactId}.git")
                    developerConnection.set("scm:git:git@github.com:paysera/${ApplicationConfiguration.artifactId}.git")
                    url.set("https://github.com/paysera/${ApplicationConfiguration.artifactId}")
                }
                developers {
                    developer {
                        id.set("payseradev")
                        name.set("Paysera App Developers")
                        email.set("app-developers@paysera.net")
                    }
                }
            }
        }
    }
    repositories {
        maven {
            val repositoryUrl = if (version.toString().endsWith("SNAPSHOT")) {
                ApplicationConfiguration.snapshotRepositoryUrl
            } else {
                ApplicationConfiguration.releaseRepositoryUrl
            }
            url = uri(repositoryUrl)
            credentials {
                username = rootProject.findProperty("ossrhUsername") as? String
                password = rootProject.findProperty("ossrhPassword") as? String
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}