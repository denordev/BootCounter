package pl.denordev.bootcounter.domain.model

data class BootEvent(
    val id: Int = 0,
    val timestamp: Long,
    val date: String,
)
