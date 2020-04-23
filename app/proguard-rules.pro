# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in <android-sdk>/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}


# ButterKnife rules
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# Retrofit rules
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

# OkHttp rules
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions


# RxJava rules
# RxAndroid will soon ship with rules so this may not be needed in the future
# https://github.com/ReactiveX/RxAndroid/issues/219
-dontwarn sun.misc.Unsafe
-keep class rx.internal.util.unsafe.** { *; }
-dontwarn rx.**


# Gson rules
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }

# Keep non static or private fields of models so Gson can find their names
#-keepclassmembers class pl.michal.tretowicz.data.model.** {
#    !static !private <fields>;
#}
#
## Some models used by gson are inner classes inside the retrofit service
#-keepclassmembers class pl.orlen.obchody.ApiService$** {
#    !static !private <fields>;
#}

# Produces useful obfuscated stack traces
# http://proguard.sourceforge.net/manual/examples.html#stacktrace
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable


# Retrolambda
-dontwarn java.lang.invoke.*

# Joda time
-dontwarn org.joda.convert.**
-dontwarn org.joda.time.**
-keep class org.joda.time.** { *; }
-keep interface org.joda.time.** { *; }

-dontwarn com.google.android.gms.location.places.**
-dontwarn com.roughike.bottombar.**

# Produces useful obfuscated stack traces
# http://proguard.sourceforge.net/manual/examples.html#stacktrace
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTablev


-keep class pl.michal.tretowicz.data.model.** { *; }
-dontwarn pl.michal.tretowicz.data.model.**

-dontwarn javax.annotation.**
-dontwarn autovalue.shaded.com.**
-dontwarn com.google.auto.value.**

# autovalue gson extension
-keep class **.AutoParcelGson_*
-keepnames @auto.parcelgson.AutoParcelGson class *

-keep class **.AutoValue_*
-keepnames @pl.michal.tretowicz.data.model.AutoGson class *

-keep class pl.michal.tretowicz.data.** { *; }
-dontwarn pl.michal.tretowicz.data.**

# fix crash in onCreateOptionsMenu on kitkat and possibly other api's as well
-keep class android.support.v7.widget.SearchView { *; }

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
