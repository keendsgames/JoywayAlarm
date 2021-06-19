# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\Software\Develop\Android\sdk/tools/proguard/proguard-android.txt
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

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


# 没有被混淆的类和成员
-printseeds seeds.txt
# 被移除的代码
-printusage usage.txt
# 混淆前后类、方法、类成员等的对照
-printmapping mapping.txt




# xml parser
-dontwarn android.content.res.XmlResourceParser
-keep class org.xmlpull.v1.** { *;}
-dontwarn org.xmlpull.v1.**


# -dontwarn android.content.Intent
# -dontwarn android.content.IntentFilter
# -dontwarn android.view.LayoutInflater

