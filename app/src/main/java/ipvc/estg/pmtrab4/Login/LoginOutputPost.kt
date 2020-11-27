package ipvc.estg.pmtrab4.Login

data class LoginOutputPost(
    val success: Boolean,
    val username: String,
    val msg : String
)

data class TicketOutputPost(
        val success: Boolean
)