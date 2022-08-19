package no.nav.tiltakspenger.testmeldinger

import mu.KotlinLogging

private val LOG = KotlinLogging.logger {}
private val SECURELOG = KotlinLogging.logger("tjenestekall")

fun main() {
    Thread.setDefaultUncaughtExceptionHandler { _, e ->
        LOG.error { "Uncaught exception logget i securelog" }
        SECURELOG.error(e) { e.message }
    }
    LOG.info { "entering main" }
    ApplicationBuilder(Configuration.rapidsAndRivers).start()
    LOG.info { "ending main" }
}
