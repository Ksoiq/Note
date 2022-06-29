class NoteService {

    private val notes = mutableListOf<Notes>()
    private val comments = mutableListOf<Comment>()
    private var noteId = 0
    private var commentId = 0

    fun add(note: Notes): Int {
        noteId++
        val newNote = note.copy(id = noteId)
        notes.add(newNote)
        return newNote.id
    }

    fun createComment(noteId: Int, comment: Comment): Int {
        val note = getById(noteId)
        commentId++
        note.comments++
        val newComment = comment.copy(id = commentId, nid = noteId)
        comments.add(newComment)
        return newComment.id
    }

    fun delete(noteId: Int): Boolean {
        val index = notes.indexOf(getById(noteId))
        notes[index].isDeleted = true
        return true
    }

    fun deleteComment(commentId: Int): Boolean {
        for (comment in comments) {
            if (commentId == comment.id) {
                comment.isDeleted = true
                return true
            }
        }
        return false
    }

    fun edit(noteId: Int, note: Notes): Boolean {
        val index = notes.indexOf(getById(noteId))
        notes[index] = note
        return true
    }

    fun editComment(commentId: Int, message: String): Boolean {
        for (comment in comments) {
            if (commentId == comment.id) {
                comment.message = message
                return true
            }
        }
        return false
    }

    fun get(): MutableList<Notes> {
        return notes
    }

    fun getById(noteId: Int): Notes {
        for (note in notes) {
            if (noteId == note.id) {
                return note
            }
        }
        throw NotFoundException("Note with id $noteId not found")
    }


    fun getComments(): MutableList<Comment> {
        return comments
    }

    fun restoreComment(commentId: Int): Boolean {
        for (comment in comments) {
            if (commentId == comment.id && comment.isDeleted) {
                comment.isDeleted = false
                return true
            }
        }
        return false
    }

}