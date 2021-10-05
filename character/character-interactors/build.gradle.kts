apply {
    from("$rootDir/library-build.gradle")
}

plugins {
    kotlin(KotlinPlugins.serialization) version Kotlin.version
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.characterDataSource))
    "implementation"(project(Modules.characterDomain))

    "implementation"(Kotlinx.coroutinesCore) // need for flows
}