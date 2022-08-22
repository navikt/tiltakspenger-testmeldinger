package no.nav.tiltakspenger.testmeldinger

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
internal class CoroutinesTest {

    private var tokenCache: Int? = null

    private suspend fun token(): Int {
        if (tokenIsSoonExpired()) {
            delay(2000L)
            refreshToken()
        }
        return tokenCache!!
    }

    private suspend fun tokenIsSoonExpired(): Boolean {
        return coroutineScope { true }
    }

    private suspend fun refreshToken() {
        coroutineScope { tokenCache = 55 }
    }

    @Test
    fun `funksjon som returnerer en verdi`() {
        assertEquals(55, runBlocking { token() })
    }
}
