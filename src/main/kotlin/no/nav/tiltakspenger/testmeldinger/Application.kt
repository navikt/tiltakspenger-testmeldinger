package no.nav.tiltakspenger.testmeldinger

import mu.KotlinLogging

fun main() {
    System.setProperty("logback.configurationFile", "egenLogback.xml")

    val log = KotlinLogging.logger {}
    val securelog = KotlinLogging.logger("tjenestekall")

    Thread.setDefaultUncaughtExceptionHandler { _, e ->
        log.error { "Uncaught exception logget i securelog" }
        securelog.error(e) { e.message }
    }
    log.info { "entering main" }
    ApplicationBuilder(Configuration.rapidsAndRivers).start()
    log.info { "ending main" }
}
