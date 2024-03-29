package com.example.apistructure.data.di

import com.example.apistructure.data.api.LoginApiService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent :: class)
object NetworkModule {
private const val  BASE_URL = "https://giftcard.builtinsoft.site/api/"
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): LoginApiService {
        return retrofit.create(LoginApiService::class.java)
    }

}
//@InstallIn(SingletonComponent::class)
//@Module
//object NetworkModule {
//
//    @Provides
//    @Singleton
//    fun provideRetroService(
//        client: OkHttpClient.Builder,
//        gson: Gson
//    ): RetroService {
//        return Retrofit.Builder()
//            .client(client.build())
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .baseUrl(BASE_URL)
//            .build()
//            .create(RetroService::class.java)
//    }
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(
//        dataStore: AppDataStore
//    ): OkHttpClient.Builder = OkHttpClient.Builder()
//        .addInterceptor {
//            val authToken = dataStore.authToken.value
//            Log.d(TAG_DEBUG, "Auth Token: $authToken")
//
//            val newRequest = it.request().newBuilder()
//                .addHeader("Authorization", "Bearer $authToken")
//                .build()
//            it.proceed(newRequest)
//        }
//
//    @Singleton
//    @Provides
//    fun provideGsonBuilder(): Gson = GsonBuilder()
//        .setLenient()
//        .create()
//
//
//}