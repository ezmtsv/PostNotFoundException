package ru.netology

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class WallServiceTestComments {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    val addPosts = fun(): Post = run {
        var last: Post? = null
        for (i in 0..5) {
            last = WallService.add(
                Post(
                    likes = null, copyright = null, donut = null,
                    geo = null, postSource = null, views = null, reposts = null, comments = null
                )
            )
        }
        return last ?: throw PostNotFoundException("ERROR adding posts")
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        WallService.createComment(addPosts().id + 1, Comment(replayAttachments = null, text = "Comment for post"))
    }

    @Test
    fun notShouldThrow() {
        WallService.createComment(addPosts().id, Comment(replayAttachments = null, text = "Comment for post"))
        val result = WallService.getComments().size
        assertEquals(result, 1)
    }

    @Test
    fun existBadComment() {
        if (WallService.reportComments().isNotEmpty()) {
            WallService.clearBadComments()
        }
        WallService.createComment(addPosts().id - 1, Comment(replayAttachments = null, text = "транзит опиума"))
        val result = WallService.reportComments().size
        println("reportComments().size = $result")
        assertEquals(result, 1)
    }
}