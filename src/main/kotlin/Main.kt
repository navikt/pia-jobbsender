fun main() {
    val applikasjonsConfig = ApplikasjonsConfig()

    println("Ber om kjøring av jobb ${applikasjonsConfig.jobb} til applikasjon ${applikasjonsConfig.applikasjon}")

    JobbSender(
        kafkaConfig = KafkaConfig()
    ).sendJobb(
        applikasjonsConfig = applikasjonsConfig
    )
}