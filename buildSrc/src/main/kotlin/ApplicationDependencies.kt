object ApplicationDependencies {

    private const val gson = "com.google.code.gson:gson:${ApplicationDependencyVersions.gson}"
    private const val jodaMoney = "org.joda:joda-money:${ApplicationDependencyVersions.jodaMoney}"
    private const val certificateTransparency = "com.appmattus.certificatetransparency:certificatetransparency:${ApplicationDependencyVersions.certificateTransparency}"
    private const val json = "org.json:json:20160212"
    private const val bolts = "com.parse.bolts:bolts-tasks:1.4.0"
    private const val retrofit = "com.squareup.retrofit2:retrofit:2.0.2"
    private const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:2.0.2"
    private const val okhttp3 = "com.squareup.okhttp3:okhttp:3.10.0"
    private const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:3.10.0"
    private const val apacheCommonsSdk = "commons-codec:commons-codec:1.10"

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