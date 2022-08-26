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

    fun sendSøknadArena() {
        LOG.info { "vi sender en søknad med tiltak fra Arenaevent" }
        // language=JSON
        val json = """
        {
          "@event_name": "søknad_mottatt",
          "søknad": {
            "id": "12008",
            "fornavn": "TALENTFULL",
            "etternavn": "BOLLE",
            "ident": "20058803546",
            "deltarKvp": false,
            "deltarIntroduksjonsprogrammet": false,
            "oppholdInstitusjon": false,
            "typeInstitusjon": null,
            "opprettet": "2021-12-20T13:08:38.444",
            "barnetillegg": [
              {
                "fornavn": "SMEKKER",
                "etternavn": "STAUDE",
                "alder": 8,
                "ident": "16081376917",
                "bosted": "NOR"
              },
              {
                "fornavn": null,
                "etternavn": null,
                "alder": 13,
                "ident": "13120875919",
                "bosted": "NOR"
              }
            ],
            "arenaTiltak": {
              "arenaId": "136347592",
              "arrangoer": "JOBLEARN AS",
              "harSluttdatoFraArena": false,
              "navn": "Jobbklubb",
              "erIEndreStatus": false,
              "opprinneligSluttdato": null,
              "opprinneligStartdato": "2021-12-06",
              "sluttdato": "2021-12-31",
              "startdato": "2021-12-06"
            },
            "brukerregistrertTiltak": null
          },
          "@id": "bc5592c4-923b-4cf7-9bb9-013fb041b560",
          "@opprettet": "2022-08-26T13:50:36.26047",
          "system_read_count": 0,
          "system_participating_services": [
            {
              "id": "bc5592c4-923b-4cf7-9bb9-013fb041b560",
              "time": "2022-08-26T13:50:36.260470"
            }
          ]
        }
        """.trimIndent()
        rapidsConnection.publish(json)
        LOG.info { "vi sendte en søknad med tiltak fra Arena event" }
    }

    fun sendSøknadBruker() {
        LOG.info { "vi sender en søknad med brukerregistrert tiltak event" }
        // language=JSON
        val json = """
            {
              "@event_name": "søknad_mottatt",
              "søknad": {
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
                }
              },
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
        LOG.info { "vi sendte en søknad med brukerregistrert tiltak event" }
    }
}
