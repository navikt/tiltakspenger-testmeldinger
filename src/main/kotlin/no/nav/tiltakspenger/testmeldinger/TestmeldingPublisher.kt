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
        rapidsConnection.publish(json)
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
        rapidsConnection.publish(json)
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
        rapidsConnection.publish(json)
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
        rapidsConnection.publish(json)
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
        rapidsConnection.publish(json)
        LOG.info { "vi sendte en institusjon behovsmelding" }
    }

    fun sendSøknad() {
        LOG.info { "vi sender en søknad event" }
        // language=JSON
        val json = """
        {
          "@event_name": "søknad_mottatt",
          "testmelding": true,
          "søknad": {
            "id": "13306",
            "fornavn": "LEVENDE",
            "etternavn": "POTET",
            "ident": "04927799109",
            "deltarKvp": false,
            "deltarIntroduksjonsprogrammet": false,
            "oppholdInstitusjon": false,
            "typeInstitusjon": null,
            "tiltaksArrangoer": "foo",
            "tiltaksType": "JOBSOK",
            "opprettet": "2022-06-29T16:24:02.608",
            "brukerRegistrertStartDato": "2022-06-21",
            "brukerRegistrertSluttDato": "2022-06-30",
            "systemRegistrertStartDato": null,
            "systemRegistrertSluttDato": null,
            "barnetillegg": []
          },
          "@id": "369bf01c-f46f-4cb9-ba0d-01beb0905edc",
          "@opprettet": "2022-06-29T16:25:33.598375671",
          "system_read_count": 1,
          "system_participating_services": [
            {
              "id": "369bf01c-f46f-4cb9-ba0d-01beb0905edc",
              "time": "2022-06-29T16:25:33.598375671",
              "service": "tiltakspenger-mottak",
              "instance": "tiltakspenger-mottak-6c65db7887-ffwcv",
              "image": "ghcr.io/navikt/tiltakspenger-mottak:2074ee7461ad748d7c99d26ee5b7374e0c7fd9f4"
            }
          ]
        }
        """.trimIndent()
        rapidsConnection.publish(json)
        LOG.info { "vi sendte en søknad event" }
    }
}
