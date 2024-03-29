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
        get("/testmeldinger/person") {
            testmeldingPublisher.sendPersonBehovTestMessage()
            call.respond("{ \"okidokey\": true }")
        }
        get("/testmeldinger/ytelser") {
            testmeldingPublisher.sendYtelserBehovTestMessage()
            call.respond("{ \"okidokey\": true }")
        }
        get("/testmeldinger/tiltak/{ident}") {
            val ident = call.parameters["ident"] ?: "05906398291"
            testmeldingPublisher.sendTiltakBehovTestMessage(ident)
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
        get("/testmeldinger/ufore") {
            val ident = call.request.queryParameters["ident"] ?: "09015607561"
            val fom = call.request.queryParameters["fom"] ?: "2022-01-01"
            val tom = call.request.queryParameters["tom"] ?: "2022-12-31"
            testmeldingPublisher.sendUføreTestMessage(ident, fom, tom)
            call.respond("{ \"okidokey\": true }")
        }
        get("/testmeldinger/overgansstonad") {
            val ident = call.request.queryParameters["ident"] ?: "09015607561"
            val fom = call.request.queryParameters["fom"] ?: "2021-01-01"
            val tom = call.request.queryParameters["tom"] ?: "2023-12-31"
            testmeldingPublisher.sendOvergangsstonadTestMessage(ident, fom, tom)
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
        get("/nydag") {
            val dag = call.request.queryParameters["dag"] ?: "2023-10-01"
            testmeldingPublisher.sendNyDag(dag)
            call.respond("{ \"okidokey\": true }")
        }
        get("/grunnlag") {
            testmeldingPublisher.sendGrunnlag()
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
