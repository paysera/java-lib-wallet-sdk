import extensions.implementation

group = "com.paysera.lib.wallet.examples"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":wallet"))
    implementation(ApplicationDependencies.dependencies)
}