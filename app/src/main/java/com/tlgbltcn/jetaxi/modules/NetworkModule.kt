package com.tlgbltcn.jetaxi.modules

import android.content.Context
import coil.util.CoilUtils
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tlgbltcn.jetaxi.BuildConfig
import com.tlgbltcn.jetaxi.data.remote.JeTaxiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val MEDIA_TYPE = "application/json".toMediaType()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Singleton
    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        val chuckerCollector = ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )

        return with(ChuckerInterceptor.Builder(context)) {
            collector(chuckerCollector)
            maxContentLength(250_000L)
            build()
        }
    }

    @Provides
    fun provideJson(): Json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        loggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient {
        return with(OkHttpClient.Builder()) {
            addInterceptor(loggingInterceptor)
            addInterceptor(chuckerInterceptor)
            cache(CoilUtils.createDefaultCache(context))
            build()
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit {
        return with(Retrofit.Builder()) {
            baseUrl(BuildConfig.API_URL)
            client(okHttpClient)
            addConverterFactory(json.asConverterFactory(MEDIA_TYPE))
            build()
        }
    }

    @Provides
    fun provideServices(retrofit: Retrofit): JeTaxiService {
        return retrofit.create(JeTaxiService::class.java)
    }
}