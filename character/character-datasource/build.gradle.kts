apply{
    from("$rootDir/library-build.gradle")
}

plugins {
    kotlin(KotlinPlugins.serialization) version Kotlin.version
}

dependencies{
    "implementation"(project(Modules.characterDomain))
    "implementation"(project(Modules.constants))

    "implementation"(Ktor.core)
    "implementation"(Ktor.clientSerialization)
    "implementation"(Ktor.clientOkHttp)
    "implementation"(Ktor.logging)
    "implementation"(Ktor.intercaptor)
}