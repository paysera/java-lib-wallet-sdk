object ApplicationDependencies {

    private const val gson = "com.google.code.gson:gson:${ApplicationDependencyVersions.gson}"
    private const val jodaMoney = "org.joda:joda-money:${ApplicationDependencyVersions.jodaMoney}"
    private const val certificateTransparency = "com.appmattus.certificatetransparency:certificatetransparency:${ApplicationDependencyVersions.certificateTransparency}"
    private const val json = "org.json:json:${ApplicationDependencyVersions.json}"
    private const val bolts = "com.parse.bolts:bolts-tasks:${ApplicationDependencyVersions.bolts}"
    private const val retrofit = "com.squareup.retrofit2:retrofit:${ApplicationDependencyVersions.retrofit}"
    private const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${ApplicationDependencyVersions.retrofit}"
    private const val okhttp3 = "com.squareup.okhttp3:okhttp:${ApplicationDependencyVersions.okhttp3}"
    private const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${ApplicationDependencyVersions.loggingInterceptor}"
    private const val apacheCommonsSdk = "commons-codec:commons-codec:${ApplicationDependencyVersions.apacheCommonsSdk}"

    // testing
    const val junit = "junit:junit:${ApplicationDependencyVersions.junit}"

    val dependencies = arrayListOf(
        gson,
        jodaMoney,
        certificateTransparency,
        json,
        bolts,
        retrofit,
        retrofitConverterGson,
        okhttp3,
        apacheCommonsSdk,
        loggingInterceptor
    )
}