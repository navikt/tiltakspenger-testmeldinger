package no.nav.tiltakspenger.testmeldinger

import mu.KotlinLogging
import net.logstash.logback.argument.StructuredArguments
import no.nav.helse.rapids_rivers.JsonMessage
import no.nav.helse.rapids_rivers.MessageContext
import no.nav.helse.rapids_rivers.MessageProblems
import no.nav.helse.rapids_rivers.RapidsConnection
import no.nav.helse.rapids_rivers.River

private val LOG = KotlinLogging.logger {}
private val SECURELOG = KotlinLogging.logger("tjenestekall")

class TestmeldingerService(
    rapidsConnection: RapidsConnection,
) : River.PacketListener {

    init {
        River(rapidsConnection).apply {
            validate {
                it.demandKey("testmelding")
                it.interestedIn("@event_name", "@behov", "@løsning", "@id", "@behovId", "ident")
            }
        }.register(this)
    }

    override fun onPacket(packet: JsonMessage, context: MessageContext) {
        runCatching {
            val id: String = packet["@id"].asText()
            val behovId: String = packet["@behovId"].asText()
            val behov: List<String> = packet["@behov"].map { it.asText() }
            val løsninger: List<String> = packet["@løsning"].fieldNames().asSequence().toList()

            setOf(LOG, SECURELOG).forEach {
                it.info(
                    "tracker testmelding med {} og {}",
                    StructuredArguments.keyValue("id", id),
                    StructuredArguments.keyValue("behovId", behovId)
                )
            }
            LOG.info { "testmelding med id $id og behovId $behovId hadde behov $behov og løsninger $løsninger " }
            SECURELOG.debug { "mottok melding: ${packet.toJson()}" }

        }.onFailure {
            loggVedFeil(it)
        }.getOrThrow()
    }

    override fun onError(problems: MessageProblems, context: MessageContext) {
        LOG.info { "meldingen validerte ikke: $problems" }
    }

    @Suppress("EmptyFunctionBlock")
    override fun onSevere(error: MessageProblems.MessageException, context: MessageContext) {
    }

    private fun loggVedFeil(ex: Throwable) {
        LOG.error("feil ${ex.message} ved behandling av testmelding, se securelogs for detaljer")
        SECURELOG.error("feil ${ex.message} ved behandling av testmelding", ex)
    }
}
