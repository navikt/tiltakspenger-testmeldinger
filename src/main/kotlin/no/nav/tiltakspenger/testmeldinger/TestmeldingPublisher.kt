package no.nav.tiltakspenger.testmeldinger

import mu.KotlinLogging
import no.nav.helse.rapids_rivers.RapidsConnection

private val LOG = KotlinLogging.logger { }

class TestmeldingPublisher(private val rapidsConnection: RapidsConnection) {

    fun sendPersonBehovTestMessage() {
        LOG.info { "vi sender en person behovsmelding" }
        // language=JSON
        val json = """
            { 
            "@behov" : ["persondata"],
            "@id" : "test",
            "@behovId": "behovId",
            "ident": "04078309135",
            "testmelding": true
            }""".trimIndent()
        rapidsConnection.publish(
            json
        )
        LOG.info { "vi sendte en person behovsmelding" }
    }

    fun sendYtelserBehovTestMessage() {
        LOG.info { "vi sender en ytelser behovsmelding" }
        // language=JSON
        val json = """
            { 
            "@behov" : ["ytelser"],
            "@id" : "test",
            "@behovId": "behovId",
            "ident": "05906398291",
            "fom" : "2019-10-01",
            "tom" : "2022-06-01",
            "testmelding": true
            }""".trimIndent()
        rapidsConnection.publish(
            json
        )
        LOG.info { "vi sendte en ytelser behovsmelding" }
    }

    fun sendTiltakBehovTestMessage() {
        LOG.info { "vi sender en tiltak behovsmelding" }
        // language=JSON
        val json = """
            { 
            "@behov" : ["tiltak"],
            "@id" : "test",
            "@behovId": "behovId",
            "ident": "05906398291",
            "testmelding": true
            }""".trimIndent()
        rapidsConnection.publish(
            json
        )
        LOG.info { "vi sendte en tiltak behovsmelding" }
    }

    fun sendSkjermingBehovTestMessage() {
        LOG.info { "vi sender en skjerming behovsmelding" }
        // language=JSON
        val json = """
            { 
            "@behov" : ["skjerming"],
            "@id" : "test",
            "@behovId": "behovId",
            "ident": "05906398291",
            "fom" : "2019-10-01",
            "tom" : "2022-06-01",
            "testmelding": true
            }""".trimIndent()
        rapidsConnection.publish(
            json
        )
        LOG.info { "vi sendte en skjerming behovsmelding" }
    }

    fun sendInstitusjonBehovTestMessage() {
        LOG.info { "vi sender en institusjon behovsmelding" }
        // language=JSON
        val json = """
            { 
            "@behov" : ["institusjon"],
            "@id" : "test",
            "@behovId": "behovId",
            "ident": "10108000398",
            "fom" : "2019-10-01",
            "tom" : "2022-06-01",
            "testmelding": true
            }""".trimIndent()
        rapidsConnection.publish(
            json
        )
        LOG.info { "vi sendte en institusjon behovsmelding" }
    }
}