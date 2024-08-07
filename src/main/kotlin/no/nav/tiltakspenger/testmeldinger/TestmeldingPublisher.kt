package no.nav.tiltakspenger.testmeldinger

import mu.KotlinLogging
import no.nav.helse.rapids_rivers.RapidsConnection

private val LOG = KotlinLogging.logger { }

class TestmeldingPublisher(private val rapidsConnection: RapidsConnection) {

    fun sendSøknadArena() {
        LOG.info { "vi sender en søknad med tiltak fra Arenaevent" }
        // language=JSON
        val json = """
        {
          "@event_name": "søknad_mottatt",
          "søknad": {
            "søknadId": "13503",
            "journalpostId": "573779888",
            "dokumentInfoId": "599041621",
            "fornavn": "DYPSINDIG",
            "etternavn": "IDÉ",
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
                },
                "trygdOgPensjon": [
                    {
                      "utbetaler": "Manchester United",
                      "prosent": null,
                      "fom": "2021-02-09",
                      "tom": "2022-08-02"
                    },
                    {
                      "utbetaler": "Bayern München",
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
        LOG.info { "vi sendte en søknad med brukerregistrert tiltak event" }
    }

    fun sendGrunnlag() {
        LOG.info { "vi sender grunnlag" }
        // language=JSON
        val json = """
            {
              "@event_name": "meldekortGrunnlag",
              "@opprettet": "2023-12-15T14:03:02.892993857",
              "meldekortGrunnlag": {
                "vedtakId": "vedtak_01HHPS5FPYDVHZ13WVPPAD1B5N",
                "behandlingId": "beh_01HCMBX9TWK1631K3G7J37117R",
                "status": "INNVILGET",
                "vurderingsperiode": {
                  "fra": "2023-11-27",
                  "til": "2024-03-22"
                },
                "tiltak": [
                  {
                    "periodeDTO": {
                      "fra": "2023-08-20",
                      "til": "2024-06-30"
                    },
                    "typeBeskrivelse": "Enkeltplass Fag- og yrkesopplæring VGS og høyere yrkesfaglig utdanning",
                    "typeKode": "ENKFAGYRKE",
                    "antDagerIUken": 0.0
                  }
                ]
              },
              "@id": "a0c65219-6b90-41ec-8b33-f9ec7382e857",
              "testmelding": true,
              "system_read_count": 0,
              "system_participating_services": [
                {
                  "id": "a0c65219-6b90-41ec-8b33-f9ec7382e857",
                  "time": "2023-12-15T14:03:02.894993063"
                }
              ]
            }
        """.trimIndent()
        rapidsConnection.publish(json)
        LOG.info { "vi sendte grunnlag" }
    }
}
