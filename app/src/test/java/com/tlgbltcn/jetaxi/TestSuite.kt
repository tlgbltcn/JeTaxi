package com.tlgbltcn.jetaxi

import android.os.Build
import com.tlgbltcn.jetaxi.data.remote.JeTaxiServiceTest
import com.tlgbltcn.jetaxi.data.mapper.MapperTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.robolectric.annotation.Config


@ExperimentalSerializationApi
@ExperimentalCoroutinesApi
@Config(sdk = [Build.VERSION_CODES.Q])
@RunWith(Suite::class)
@Suite.SuiteClasses(
    MapperTest::class,
    JeTaxiServiceTest::class
)
class TestSuite
