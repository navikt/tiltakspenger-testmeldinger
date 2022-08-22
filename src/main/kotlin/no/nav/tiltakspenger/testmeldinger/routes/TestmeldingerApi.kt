package no.nav.tiltakspenger.testmeldinger.routes

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.ktor.serialization.jackson.jackson
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import mu.KotlinLogging
import no.nav.tiltakspenger.testmeldinger.TestmeldingPublisher

private val LOG = KotlinLogging.logger { }

internal fun Application.testmeldingerAPI(testmeldingPublisher: TestmeldingPublisher) {
    jacksonSerialization()
    LOG.info("Setting up routing")
    routing {
        LOG.info("Setting up testmeldinger path")
        get("/testmeldinger") {
            testmeldingPublisher.sendPersonBehovTestMessage()
            testmeldingPublisher.sendYtelserBehovTestMessage()
            testmeldingPublisher.sendTiltakBehovTestMessage()
            testmeldingPublisher.sendSkjermingBehovTestMessage()
            testmeldingPublisher.sendInstitusjonBehovTestMessage()
            call.respond("{ \"okidokey\": true }")
        }
        LOG.info("Setting up soknad path")
        get("/soknad") {
            testmeldingPublisher.sendSøknad()
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
