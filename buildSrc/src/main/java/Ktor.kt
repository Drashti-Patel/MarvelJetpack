object Ktor {
    private const val ktorVersion = "1.5.2"
    private const val loggingIntercaptorVersion = "4.9.1"

    const val core = "io.ktor:ktor-client-core:$ktorVersion"
    const val clientSerialization = "io.ktor:ktor-client-serialization:$ktorVersion"
    const val clientOkHttp = "io.ktor:ktor-client-okhttp:$ktorVersion"
    const val logging = "io.ktor:ktor-client-logging-jvm:$ktorVersion"
    const val intercaptor = "com.squareup.okhttp3:logging-interceptor:$loggingIntercaptorVersion"
}