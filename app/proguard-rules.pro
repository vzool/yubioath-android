# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Dain\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
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

# The Nordpol library contains methods that will be invoked by the
# system and will therefore be removed by proguard. This forces
# proguard to keep those methods.
-keep class nordpol.** { *; }

# The Nordpol library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn nordpol.android.**

-dontwarn kotlin.**

# These are only needed for Anko 0.9.0, and should be removed when Anko 0.9.1 is out.
-keep class org.jetbrains.anko.internals.** { *; }
-dontwarn org.jetbrains.anko.internals.**