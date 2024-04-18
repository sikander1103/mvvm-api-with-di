package com.example.apistructure.data.di

import android.content.Context
import android.util.Log
import com.example.apistructure.Session
import com.example.apistructure.data.api.LoginApiService
import com.example.apistructure.presentation.Keys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent :: class)
object NetworkModule {
private const val  BASE_URL = "https://giftcard.builtinsoft.site/api/"
    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context, session: Session): Retrofit {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor { chain ->
                // Add token to headers if available
                val token = session.get(Keys.KEY_TOKEN)
//                val token = getTokenFromSharedPreferences(context)

                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(request)
            }

            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun provideSession(@ApplicationContext context: Context): Session {
        return Session(context)
    }
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): LoginApiService {
        return retrofit.create(LoginApiService::class.java)
    }
//    private const val TOKEN_KEY = "token_key"

//    fun saveTokenToSharedPreferences(context: Context, token: String) {
//        val sharedPreferences = context.getSharedPreferences("my_app_pref", Context.MODE_PRIVATE)
//        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
//    }
//
//    fun getTokenFromSharedPreferences(context: Context): String? {
//        val sharedPreferences = context.getSharedPreferences("my_app_pref", Context.MODE_PRIVATE)
//        return sharedPreferences.getString(TOKEN_KEY, null)
//    }

}
