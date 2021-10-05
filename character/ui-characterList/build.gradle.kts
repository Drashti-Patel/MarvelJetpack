apply{
    from("$rootDir/android-library-build.gradle")
}
dependencies{
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.characterDomain))
    "implementation"(project(Modules.characterInteractors))

    "implementation"(Coil.coil)

    "implementation"(Hilt.android)
    "kapt"(Hilt.compiler)
}