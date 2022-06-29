data class Notes(
    val id: Int,
    var title: String,
    var text: String,
    val date: Long,
    var comments: Int,
    val readComments: Int?,
    val viewUrl: String,
    var privacyView: String,
    val canComment: Boolean = true,
    val textWiki: String,
    var isDeleted: Boolean = false
)

data class Comment(
    val id: Int,
    val uid: Int,
    val nid: Int,
    val oid: Int,
    val date: Long,
    var message: String,
    val replyTo: Int?,
    var isDeleted: Boolean = false
)

// -guid, ownerId


