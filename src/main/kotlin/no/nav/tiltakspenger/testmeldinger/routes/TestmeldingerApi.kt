package no.nav.tiltakspenger.testmeldinger.routes

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import mu.KotlinLogging
import no.nav.tiltakspenger.testmeldinger.TestmeldingPublisher

private val LOG = KotlinLogging.logger { }

internal fun Application.testmeldingerAPI(testmeldingPublisher: TestmeldingPublisher) {
    jacksonSerialization()
    LOG.info("Setting up routing")
    routing {
        LOG.info("Setting up testmeldinger path")
        get("/testmeldinger/person") {
            testmeldingPublisher.sendPersonBehovTestMessage()
            call.respond("{ \"okidokey\": true }")
        }
        get("/testmeldinger/ytelser") {
            testmeldingPublisher.sendYtelserBehovTestMessage()
            call.respond("{ \"okidokey\": true }")
        }
        get("/testmeldinger/tiltak") {
            testmeldingPublisher.sendTiltakBehovTestMessage()
            call.respond("{ \"okidokey\": true }")
        }
        get("/testmeldinger/skjerming") {
            testmeldingPublisher.sendSkjermingBehovTestMessage()
            call.respond("{ \"okidokey\": true }")
        }
        get("/testmeldinger/inst") {
            testmeldingPublisher.sendInstitusjonBehovTestMessage()
            call.respond("{ \"okidokey\": true }")
        }
        get("/testmeldinger/fp") {
            testmeldingPublisher.sendFpTestMessage()
            call.respond("{ \"okidokey\": true }")
        }
        LOG.info("Setting up soknad path")
        get("/soknad/arena") {
            testmeldingPublisher.sendSøknadArena()
            call.respond("{ \"okidokey\": true }")
        }
        get("/soknad/bruker") {
            testmeldingPublisher.sendSøknadBruker()
            call.respond("{ \"okidokey\": true }")
        }
    }
}

fun Application.jacksonSerialization() {
    install(ContentNegotiation) {
        jackson {
            configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            registerModule(JavaTimeModule())
            registerModule(KotlinModule.Builder().build())
        }
    }
}
