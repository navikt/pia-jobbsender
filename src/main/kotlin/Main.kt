import org.slf4j.LoggerFactory

fun main() {
    val applikasjonsConfig = ApplikasjonsConfig()

    val logger = LoggerFactory.getLogger("mainlogger")
    logger.info("Ber om kjøring av jobb '${applikasjonsConfig.jobb}' til applikasjon ${applikasjonsConfig.applikasjon}")

    JobbSender(
        kafkaConfig = KafkaConfig(),
    ).sendJobb(
        applikasjonsConfig = applikasjonsConfig,
    )

    logger.info("Ferdig med å sende jobb '${applikasjonsConfig.jobb}' til applikasjon ${applikasjonsConfig.applikasjon}")
}
