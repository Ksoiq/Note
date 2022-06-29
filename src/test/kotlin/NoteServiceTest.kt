import org.junit.Assert.*
import org.junit.Test
import kotlin.time.Duration.Companion.milliseconds

class NoteServiceTest {


    @Test
    fun add() {
        val notes = NoteService()
        notes.add(
            Notes(
                1,
                "tittle",
                "Text",
                26062022,
                1,
                null,
                "URL",
                "privacyView",
                true,
                "wiki",
                false
            )
        )
        assertEquals(1, notes.get().size)
    }

    @Test
    fun createComment() {
        val notes = NoteService()
            val comment1 = Comment(
                1,
                1,
                10,
                1,
                20202222,
                "message",
                null
            )
            val comment2 = Comment(
                2,
                1,
                17,
                1,
                2020232222,
                "messasdadge",
                null
            )

        val testNote = Notes(
            1,
            "tittle",
            "Text",
            26062022,
            0,
            null,
            "URL",
            "privacyView",
            true,
            "wiki",
            false
        )

        notes.add(testNote)
        notes.createComment(1, comment1)
        notes.createComment(1, comment2)

        assertEquals(2, notes.getComments().size)
        assertEquals(1, notes.getComments()[0].nid)
        assertEquals(1, notes.getComments()[1].nid)
        assertEquals(2, notes.getById(1).comments)
    }

    @Test
    fun delete() {
        val notes = NoteService()
        val testNote = Notes(
            1,
            "tittle",
            "Text",
            26062022,
            1,
            null,
            "URL",
            "privacyView",
            true,
            "wiki",
            false
        )

        notes.add(testNote)

        assertTrue(notes.delete(1))


    }

    @Test
    fun deleteComment() {
        val notes = NoteService()
        val comment1 = Comment(
            1,
            1,
            1,
            1,
            20202222,
            "message",
            null
        )

        val comment2 = Comment(
            1,
            1,
            1,
            1,
            202202222,
            "messaффge",
            null
        )

        val testNote = Notes(
            1,
            "tittle",
            "Text",
            26062022,
            1,
            null,
            "URL",
            "privacyView",
            true,
            "wiki",
            false
        )

        notes.add(testNote)

        notes.createComment(1, comment1)
        notes.createComment(1, comment2)

        assertTrue(notes.deleteComment(1))
        assertFalse(notes.deleteComment(99))


    }


    @Test
    fun edit() {
        val notes = NoteService()
        val testNote = Notes(
            1,
            "tittle",
            "Text",
            26062022,
            1,
            null,
            "URL",
            "privacyView",
            true,
            "wiki",
            false
        )
        val editedNote = Notes(
            2,
            "tittle",
            "Text+++++",
            26062022,
            1,
            null,
            "URL",
            "fdsgr",
            true,
            "wiki",
            false
        )

        notes.add(testNote)

        assertTrue(notes.edit(1, editedNote))
    }

    @Test(expected = NotFoundException::class)
    fun failEdit() {
        val notes = NoteService()
        val editedNote = Notes(
            1,
            "tittle",
            "Text",
            26062022,
            1,
            null,
            "URL",
            "privacyView",
            true,
            "wiki",
            false
        )

        notes.edit(1, editedNote)

    }

    @Test
    fun editComment() {
        val notes = NoteService()
        val comment1 = Comment(
            1,
            1,
            1,
            1,
            20202222,
            "message",
            null
        )
        val newMessage = "new message"
        val testNote = Notes(
            1,
            "tittle",
            "Text",
            26062022,
            1,
            null,
            "URL",
            "privacyView",
            true,
            "wiki",
            false
        )

        notes.add(testNote)
        notes.createComment(1, comment1)
        notes.editComment(1, newMessage)

        assertEquals(newMessage, notes.getComments()[0].message)

    }

    @Test
    fun failEditComment() {
        val notes = NoteService()
        val comment1 = Comment(
            1,
            1,
            1,
            1,
            20202222,
            "message",
            null
        )
        val newMessage = "new message"
        val testNote = Notes(
            1,
            "tittle",
            "Text",
            26062022,
            1,
            null,
            "URL",
            "privacyView",
            true,
            "wiki",
            false
        )

        notes.add(testNote)
        notes.createComment(1, comment1)

        assertFalse(notes.editComment(2, newMessage))
    }

    @Test
    fun getById() {
        val notes = NoteService()
        val testNote = Notes(
            1,
            "tittle",
            "Text",
            26062022,
            1,
            null,
            "URL",
            "privacyView",
            true,
            "wiki",
            false
        )

        notes.add(testNote)

        assertEquals(testNote, notes.getById(1))

    }

    @Test(expected = NotFoundException::class)
    fun failGetById() {
        val notes = NoteService()

        notes.getById(1)

    }

    @Test
    fun restoreComment() {
        val notes = NoteService()
        val comment1 = Comment(
            1,
            1,
            1,
            1,
            20202222,
            "message",
            null,
        )

        val testNote = Notes(
            1,
            "tittle",
            "Text",
            26062022,
            1,
            null,
            "URL",
            "privacyView",
            true,
            "wiki",
            false
        )

        notes.add(testNote)
        notes.createComment(1, comment1)
        notes.deleteComment(1)

        assertTrue(notes.restoreComment(1))
    }

    @Test
    fun failRestoreComment() {
        val notes = NoteService()
        val comment1 = Comment(
            1,
            1,
            1,
            1,
            20202222,
            "message",
            null
        )

        val testNote = Notes(
            1,
            "tittle",
            "Text",
            26062022,
            1,
            null,
            "URL",
            "privacyView",
            true,
            "wiki",
            false
        )

        notes.add(testNote)
        notes.createComment(1, comment1)

        assertFalse(notes.restoreComment(1))
        assertFalse(notes.restoreComment(33))

    }

}