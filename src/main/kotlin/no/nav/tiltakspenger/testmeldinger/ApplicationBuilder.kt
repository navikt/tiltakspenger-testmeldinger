package no.nav.tiltakspenger.testmeldinger

import mu.KotlinLogging
import no.nav.helse.rapids_rivers.RapidApplication
import no.nav.helse.rapids_rivers.RapidsConnection
import no.nav.tiltakspenger.testmeldinger.routes.testmeldingerAPI

private val LOG = KotlinLogging.logger { }

internal class ApplicationBuilder(config: Map<String, String>) : RapidsConnection.StatusListener {

    private val rapidsConnection: RapidsConnection = RapidApplication.Builder(
        RapidApplication.RapidApplicationConfig.fromEnv(config)
    ).withKtorModule {
        // naisRoutes() Denne var ikke i bruk i -vedtak, så jeg kommenterer den ut her også
        testmeldingerAPI(testmeldingPublisher())
    }.build()

    private val testmeldingPublisher = TestmeldingPublisher(rapidsConnection = rapidsConnection)
    //private val testmeldingerService = TestmeldingerService(rapidsConnection = rapidsConnection)

    init {
        TestmeldingerService(rapidsConnection = rapidsConnection)
        rapidsConnection.register(this)
    }

    fun start() {
        rapidsConnection.start()
    }

    override fun onStartup(rapidsConnection: RapidsConnection) {
        LOG.info { "Starting tiltakspenger-testmeldinger" }
    }

    override fun onShutdown(rapidsConnection: RapidsConnection) {
        LOG.info { "Stopping tiltakspenger-testmeldinger" }
        //super.onShutdown(rapidsConnection)
    }

    private fun testmeldingPublisher(): TestmeldingPublisher = testmeldingPublisher
}
