package no.nav.tiltakspenger.testmeldinger

import mu.KotlinLogging
import no.nav.helse.rapids_rivers.RapidsConnection

private val LOG = KotlinLogging.logger { }

class TestmeldingPublisher(private val rapidsConnection: RapidsConnection) {

    fun sendPersonBehovTestMessage() {
        LOG.info { "vi sender en personopplysninger behovsmelding" }
        // language=JSON
        val json = """
            { 
            "@behov" : ["personopplysninger"],
            "@id" : "test",
            "@behovId": "behovId",
            "ident": "04078309135",
            "testmelding": true
            }""".trimIndent()
        rapidsConnection.publish(json)
        LOG.info { "vi sendte en personopplysninger behovsmelding" }
    }

    fun sendYtelserBehovTestMessage() {
        LOG.info { "vi sender en ytelser behovsmelding" }
        // language=JSON
        val json = """
            { 
            "@behov" : ["arenaytelser"],
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
            "@behov" : ["arenatiltak"],
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

    @Suppress("LongMethod")
    fun sendS??knadArena() {
        LOG.info { "vi sender en s??knad med tiltak fra Arenaevent" }
        // language=JSON
        val json = """
        {
          "@event_name": "s??knad_mottatt",
          "s??knad": {
            "s??knadId": "13503",
            "journalpostId": "573779888",
            "dokumentInfoId": "599041621",
            "fornavn": "DYPSINDIG",
            "etternavn": "ID??",
            "ident": "05906398291",
            "deltarKvp": false,
            "deltarIntroduksjonsprogrammet": false,
            "oppholdInstitusjon": false,
            "typeInstitusjon": null,
            "opprettet": "2022-09-14T16:02:25.711",
            "barnetillegg": [],
            "arenaTiltak": {
              "arenaId": "138377101",
              "arrangoer": "STENDI SENIOR AS",
              "harSluttdatoFraArena": true,
              "tiltakskode": "ARBTREN",
              "erIEndreStatus": false,
              "opprinneligSluttdato": "2022-08-31",
              "opprinneligStartdato": "2022-07-04",
              "sluttdato": "2022-08-31",
              "startdato": "2022-07-04"
            },
            "brukerregistrertTiltak": null,
            "trygdOgPensjon": null,
            "fritekst": "fritekst fra pj"
          },
          "@id": "43942a8b-79ff-47ba-a873-9270dcdb96a8",
          "@opprettet": "2022-09-14T16:03:31.559217697",
          "system_read_count": 0,
          "system_participating_services": [
            {
              "id": "43942a8b-79ff-47ba-a873-9270dcdb96a8",
              "time": "2022-09-14T16:03:31.559217697",
              "service": "tiltakspenger-mottak",
              "instance": "tiltakspenger-mottak-79bf794c96-pdc5q",
              "image": "ghcr.io/navikt/tiltakspenger-mottak:7e4e29e84c524a8d47a2dc9c2585f1f1fe13bdac"
            }
          ]
      }""".trimIndent()
        rapidsConnection.publish(json)
        LOG.info { "vi sendte en s??knad med tiltak fra Arena event" }
    }

    fun sendS??knadBruker() {
        LOG.info { "vi sender en s??knad med brukerregistrert tiltak event" }
        // language=JSON
        val json = """
            {
              "@event_name": "s??knad_mottatt",
              "s??knad": {
                "id": "12918",
                "fornavn": "USNOBBET",
                "etternavn": "BJELKE",
                "ident": "07878896291",
                "deltarKvp": false,
                "deltarIntroduksjonsprogrammet": true,
                "oppholdInstitusjon": false,
                "typeInstitusjon": null,
                "opprettet": "2022-04-08T15:39:56.242",
                "barnetillegg": [],
                "arenaTiltak": null,
                "brukerregistrertTiltak": {
                  "tiltakstype": "Annet",
                  "arrangoernavn": "test as",
                  "beskrivelse": "Intro",
                  "fom": "2022-04-01",
                  "tom": "2022-04-22",
                  "adresse": "Storgata 1",
                  "postnummer": "0318",
                  "antallDager": 5
                },
                "trygdOgPensjon": [
                    {
                      "utbetaler": "Manchester United",
                      "prosent": null,
                      "fom": "2021-02-09",
                      "tom": "2022-08-02"
                    },
                    {
                      "utbetaler": "Bayern M??nchen",
                      "prosent": 30,
                      "fom": "2022-08-10",
                      "tom": null
                    }
                ]
              },
              "fritekst": "en fritekst",
              "@id": "bcf2e0e7-e9ad-494e-9ec2-3300b790d224",
              "@opprettet": "2022-08-26T13:52:58.645834",
              "system_read_count": 0,
              "system_participating_services": [
                {
                  "id": "bcf2e0e7-e9ad-494e-9ec2-3300b790d224",
                  "time": "2022-08-26T13:52:58.645834"
                }
              ]
            }
        """.trimIndent()
        rapidsConnection.publish(json)
        LOG.info { "vi sendte en s??knad med brukerregistrert tiltak event" }
    }

    fun sendFpTestMessage() {
        LOG.info { "vi sender en fp behovsmelding" }
        // language=JSON
        val json = """
            {
            "@behov" : ["fpytelser"],
            "@id" : "test",
            "@behovId": "behovId",
            "ident": "08479137723",
            "fom" : "2003-10-01",
            "tom" : "2022-12-30",
            "testmelding": true
            }""".trimIndent()
        rapidsConnection.publish(json)
        LOG.info { "vi sendte en fp behovsmelding" }
    }

    fun sendUf??reTestMessage(ident: String, fom: String, tom: String) {
        LOG.info { "vi sender en uf??re behovsmelding" }
        // language=JSON
        val json = """
            {
            "@behov" : ["uf??re"],
            "@id" : "test",
            "@behovId": "behovId",
            "ident": "$ident",
            "fom" : "$fom",
            "tom" : "$tom",
            "testmelding": true
            }""".trimIndent()
        rapidsConnection.publish(json)
        LOG.info { "vi sendte en uf??re behovsmelding" }
    }
}
