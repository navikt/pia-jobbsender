import kotlin.test.Test

class JobbSenderTest {
    private val jobbSender = JobbSender(
        KafkaConfig(
            brokers = "localhost:9092",
            truststoreLocation = "",
            keystoreLocation = "",
            credstorePassword = "",
            jobblytterTopic = "",
            clientId = "",
        ),
    )

    @Test
    fun `sjekk jsonifisering`() {
        val json = jobbSender.jsonifiser("jobb", "applikasjon")
        assert(json.contains("\"jobb\": \"jobb\","))
        assert(json.contains("\"applikasjon\": \"applikasjon\","))
        assert(json.contains("\"tidspunkt\": \""))
    }
}
