import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import java.time.Clock

class JobbSender(
    private val kafkaConfig: KafkaConfig,
) {
    fun sendJobb(applikasjonsConfig: ApplikasjonsConfig) {
        KafkaProducer<String, String>(
            kafkaConfig.producerProperties(),
        ).use {
            it.send(
                ProducerRecord(
                    kafkaConfig.jobblytterTopic,
                    applikasjonsConfig.jobb,
                    jsonifiser(applikasjonsConfig.jobb, applikasjonsConfig.applikasjon, applikasjonsConfig.parameter),
                ),
            )
        }
    }

    fun jsonifiser(
        jobb: String,
        applikasjon: String,
        parameter: String
    ) = """
        {
            "jobb": "$jobb",
            "applikasjon": "$applikasjon",
            "parameter": "$parameter",
            "tidspunkt": "${Clock.systemUTC().instant()}"
        }
        """.trimIndent()
}
