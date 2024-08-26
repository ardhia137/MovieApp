##---------------Begin: Proguard configuration for SQLCipher  ----------
-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }

##---------------Begin: Proguard configuration for Gson  ----------
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn sun.misc.**
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

##---------------Begin: Proguard configuration for Retrofit  ----------
-keepattributes Signature, InnerClasses, EnclosingMethod
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn javax.annotation.**
-dontwarn kotlin.Unit
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>
-dontwarn kotlinx.**

##---------------Begin: Proguard configuration for Glide  ----------
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}
-dontwarn javax.annotation.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-dontwarn org.codehaus.mojo.animal_sniffer.*
-dontwarn okhttp3.internal.platform.ConscryptPlatform
-dontwarn org.conscrypt.ConscryptHostnameVerifier
-dontwarn com.airbnb.lottie.**
-keep class com.airbnb.lottie.** {*;}

##---------------Begin: Proguard configuration for your app  ----------
# Keep Resource classes and their members
-keep class com.example.movieapp.core.data.Resource$Error { *; }
-keep class com.example.movieapp.core.data.Resource$Loading { *; }
-keep class com.example.movieapp.core.data.Resource$Success { *; }
-keep class com.example.movieapp.core.data.Resource { *; }

# Keep DataSource classes and their members
-keep class com.example.movieapp.core.data.source.local.LocalDataSource { *; }
-keep class com.example.movieapp.core.data.source.remote.RemoteDataSource { *; }
-keep class com.example.movieapp.core.data.source.remote.network.ApiService { *; }
-keep class com.example.movieapp.core.data.source.local.room.MovieDao { *; }

# Keep DI modules and their providers
-keep class com.example.movieapp.core.di.** { *; }
# Keep domain classes and their members
-keep class com.example.movieapp.core.domain.model.Movie { *; }
-keep class com.example.movieapp.core.domain.repository.IMovieRepository { *; }
-keep class com.example.movieapp.core.domain.usecase.MovieInteractor { *; }
-keep class com.example.movieapp.core.domain.usecase.MovieUseCase { *; }

# Keep UI classes and their members
-keep class com.example.movieapp.core.ui.MovieAdapter { *; }

-keep class com.example.movieapp.** { *; }


# Keep utility classes
-keep class com.example.movieapp.core.utils.AppExecutors { *; }

# Keep annotations
-keepattributes *Annotation*
