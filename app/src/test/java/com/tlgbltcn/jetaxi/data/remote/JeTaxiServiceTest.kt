package com.tlgbltcn.jetaxi.data.remote

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tlgbltcn.jetaxi.data.model.TaxisDataModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.internal.bytecode.RobolectricInternals
import retrofit2.Retrofit
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
@ExperimentalSerializationApi
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.Q])
class JeTaxiServiceTest {

    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private val jsonBuilder = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    private lateinit var jsonString: String

    lateinit var expectedData: TaxisDataModel

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(jsonBuilder.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(JeTaxiService::class.java)

    @ExperimentalSerializationApi
    @Before
    fun setup() {
        jsonString = RobolectricInternals.getClassLoader()
            .getResourceAsStream("test.json")
            .bufferedReader()
            .use {
                it.readText()
            }

        expectedData = jsonBuilder.decodeFromString(jsonString)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `the service should fetch movies correctly given 200 response`() = runBlocking {
        mockWebServer.enqueueResponse("test.json", 200)

        val actual = api.getTaxis()

        Truth.assertThat(actual.body()).isEqualTo(expectedData)
    }

    @Test
    fun `the api should fetch movies and encountered error`() = runBlocking {
        mockWebServer.enqueueResponse("test.json", 400)

        val actual = api.getTaxis()

        Truth.assertThat(actual.body()).isNotEqualTo(expectedData)
    }
}

private fun MockWebServer.enqueueResponse(fileName: String, code: Int) {
    javaClass.classLoader?.getResourceAsStream(fileName)
        ?.source()
        ?.buffer()
        .also { source ->
            source?.run {
                enqueue(
                    MockResponse()
                        .setResponseCode(code)
                        .setBody(source.readString(StandardCharsets.UTF_8))
                )
            }
        }
}
