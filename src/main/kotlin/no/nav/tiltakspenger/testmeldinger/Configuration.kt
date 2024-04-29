package no.nav.tiltakspenger.testmeldinger

import com.natpryce.konfig.ConfigurationMap
import com.natpryce.konfig.ConfigurationProperties.Companion.systemProperties
import com.natpryce.konfig.EnvironmentVariables
import com.natpryce.konfig.Key
import com.natpryce.konfig.overriding
import com.natpryce.konfig.stringType

object Configuration {

    val rapidsAndRivers = mapOf(
        "RAPID_APP_NAME" to "tiltakspenger-testmeldinger",
        "KAFKA_BROKERS" to System.getenv("KAFKA_BROKERS"),
        "KAFKA_CREDSTORE_PASSWORD" to System.getenv("KAFKA_CREDSTORE_PASSWORD"),
        "KAFKA_TRUSTSTORE_PATH" to System.getenv("KAFKA_TRUSTSTORE_PATH"),
        "KAFKA_KEYSTORE_PATH" to System.getenv("KAFKA_KEYSTORE_PATH"),
        "KAFKA_RAPID_TOPIC" to "tpts.rapid.v1",
        "KAFKA_RESET_POLICY" to "latest",
        "KAFKA_CONSUMER_GROUP_ID" to "tiltakspenger-testmeldinger-v1",
        "HTTP_PORT" to "8088",
    )

    private val otherDefaultProperties = mapOf(
        "application.httpPort" to 8088.toString(),
    )
    private val defaultProperties = ConfigurationMap(rapidsAndRivers + otherDefaultProperties)

    private val localProperties = ConfigurationMap(
        mapOf(
            "application.profile" to Profile.LOCAL.toString(),
        ),
    )
    private val devProperties = ConfigurationMap(
        mapOf(
            "application.profile" to Profile.DEV.toString(),
        ),
    )

    fun applicationProfile() = when (System.getenv("NAIS_CLUSTER_NAME") ?: System.getProperty("NAIS_CLUSTER_NAME")) {
        "dev-gcp" -> Profile.DEV
        else -> Profile.LOCAL
    }

    private fun config() = when (System.getenv("NAIS_CLUSTER_NAME") ?: System.getProperty("NAIS_CLUSTER_NAME")) {
        "dev-gcp" ->
            systemProperties() overriding EnvironmentVariables overriding devProperties overriding defaultProperties

        else -> {
            systemProperties() overriding EnvironmentVariables overriding localProperties overriding defaultProperties
        }
    }

    fun kafkaBootstrapLocal(): String = config()[Key("KAFKA_BROKERS", stringType)]
}

enum class Profile {
    LOCAL, DEV
}
