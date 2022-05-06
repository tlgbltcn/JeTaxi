package com.tlgbltcn.jetaxi.data.mapper

import android.os.Build.VERSION_CODES.Q
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tlgbltcn.jetaxi.data.model.TaxisDataModel
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.internal.bytecode.RobolectricInternals.getClassLoader

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Q])
class MapperTest {

    private val coordinateMapper = CoordinateMapper()
    private val poiMapper = PoiMapper(coordinateMapper = coordinateMapper)
    private val taxisMapper = TaxisMapper(poiMapper = poiMapper)

    private val jsonBuilder = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
        encodeDefaults = false
    }

    private lateinit var json: String

    lateinit var data: TaxisDataModel

    @ExperimentalSerializationApi
    @Before
    fun setup() {
        json = getClassLoader()
            .getResourceAsStream("test.json")
            .bufferedReader()
            .use {
                it.readText()
            }

        data = jsonBuilder.decodeFromString(json)
    }

    @Test
    fun `taxis mapper should return the given object size`() {

        val result = taxisMapper.map(data)

        assert(result.poiList.get(0).id == data.poiList?.get(0)?.id)
    }

    @Test
    fun `poiList shouldn't be empty or null`() {

        val result = poiMapper.map(data.poiList)

        assert(!result.isNullOrEmpty())
    }

    @Test
    fun `poiList size should be equal the given list size`() {
        val result = poiMapper.map(data.poiList)

        assert(result.size == data.poiList?.size)
    }

    @Test
    fun `coordinate should be equal the given latitude and longitude`() {
        val latitude = data.poiList?.get(0)?.coordinate?.latitude
        val longitude = data.poiList?.get(0)?.coordinate?.longitude

        val result = coordinateMapper.map(data.poiList?.get(0)?.coordinate)

        assert(result.latitude == latitude && result.longitude == longitude)
    }
}
