# Keep the names of classes and members used in XML layouts
-keepclassmembers class * {
    @androidx.annotation.Keep *;
}

# Keep the names of classes and members used by reflection
-keepclassmembers class * {
    @androidx.annotation.Keep *;
}

# Keep Retrofit and Gson classes
-keep class com.google.gson.** { *; }
-keep class retrofit2.** { *; }
-keep class okhttp3.** { *; }
-keep class com.multiclock.app.api.** { *; }
-keep class com.multiclock.app.models.** { *; }

# Keep the names of classes and members used in Retrofit interfaces
-keep interface com.multiclock.app.api.** { *; }

# Keep the names of classes and members used in Retrofit models
-keep class com.multiclock.app.models.** { *; }

# Add any additional ProGuard rules here
# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
-dontwarn org.conscrypt.Conscrypt$Version
-dontwarn org.conscrypt.Conscrypt
-dontwarn org.conscrypt.ConscryptHostnameVerifier
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE