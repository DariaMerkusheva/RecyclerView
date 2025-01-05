package otus.gpb.recyclerview

data class ChatItem(
    val id: Int,
    val imageId: Int,
    val name: String,
    val status: String,
    val message: String,
    val counter: Int,
    val verified: Boolean,
    val voice: Boolean,
    val mute: Boolean,
    val scam: Boolean,
    val delivered: Boolean,
    val read: Boolean,
    val dateTime: String,
)