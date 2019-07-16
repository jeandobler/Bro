package com.dobler.bro

import com.dobler.bro.api.BroService
import com.dobler.bro.di.AppModule
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit.WireMockRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject


@RunWith(JUnit4::class)
class ExampleUnitTest : KoinTest {

    val api: BroService by inject()

    val hello = module {
        single {
            AppModule.networkModule
        }
    }

    @Rule
    @JvmField
    var wireMockRule = WireMockRule(8089)

    @Before
    fun setupNetworkLayer() {
        startKoin {
            modules(AppModule.networkModule)
        }
    }


    @Test
    fun testSomeUI() {

        stubFor(
            get(urlEqualTo("/users"))
                .willReturn(
                    aResponse().withBody("Hello World!")
                )
        )

        runBlocking {

            launch(Dispatchers.Main) {
                // Will be launched in the mainThreadSurrogate dispatcher
                var resp = api.getUser(null, null)
//                println(3333333333)
                println(resp.get(1).avatar)
//                println(3333333333)
                Assert.assertEquals(resp, "Hello World!")
            }
        }
    }

}
