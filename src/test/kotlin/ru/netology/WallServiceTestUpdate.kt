package ru.netology

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceTestUpdate {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    private val post: Post = Post(
//        id = 15,
        ownerId = 25,
        fromId = 35,
        createdBy = 50,
        date = 1690848789L,
        text = "Test message",
        friendsOnly = true,
        replyOwnerId = 18,
        replyPostId = 29,
        canPin = false,
        canDelete = false,
        copyright = CopyRight(88, "ru.netology", "Ivan", "user"),
        likes = Likes(145, userLikes = true, canLike = false, canPublish = false),
        donut = null,
        geo = null,
        postSource = null,
        views = null,
        reposts = null,
        comments = null
    )
    private val post1: Post = Post(
//        id = 15,
        ownerId = 15,
        fromId = 25,
        createdBy = 50,
        date = 1690848789L,
        text = "Test message",
        friendsOnly = false,
        replyOwnerId = 18,
        replyPostId = 29,
        canPin = false,
        canDelete = false,
        copyright = CopyRight(88, "ru.netology", "Ivan", "user"),
        likes = Likes(145, userLikes = true, canLike = false, canPublish = false),
        donut = null,
        geo = null,
        postSource = null,
        views = null,
        reposts = null,
        comments = null
    )
    private val post2: Post = Post(
//        id = 5,
        ownerId = 15,
        fromId = 25,
        createdBy = 50,
        date = 1690848789L,
        text = "Test message",
        friendsOnly = false,
        replyOwnerId = 18,
        replyPostId = 29,
        canPin = false,
        canDelete = false,
        copyright = CopyRight(88, "ru.netology", "Ivan", "user"),
        likes = Likes(145, userLikes = true, canLike = false, canPublish = false),
        donut = null,
        geo = null,
        postSource = null,
        views = null,
        reposts = null,
        comments = null
    )

    @Test
    fun updatePostTrue() {
        WallService.add(post)
        val result = WallService.update(post1)
        assertTrue(result)
    }

    @Test
    fun updatePostFalse() {
        val result = WallService.update(post2)
        assertFalse(result)
    }
}