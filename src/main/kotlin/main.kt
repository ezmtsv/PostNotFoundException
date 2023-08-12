package ru.netology

import java.net.URL

class Comment(
    val id: Int = 0,
    val fromId: Int = 0,
    val date: Int = 0,
    val text: String = "text",
    val replayToUser: Int = 0,
    val replayToComment: Int = 0,
    val replayAttachments: Attachment?,
    val parentsStack: Array<Int> = emptyArray()
)

class BadComment(
    val ownerId: Int = 0,
    val commentId: Int = 0,
    val reason: Int = 0
)

class PostNotFoundException(message: String) : RuntimeException(message)
object WallService {
    private var comments = emptyArray<Comment>()
    private var badComments = emptyArray<BadComment>()
    private var posts = emptyArray<Post>()
    var lastPost = 0

//    fun createComment(postId: Int, comment: Comment): Comment {
//        findById(postId) ?: throw PostNotFoundException("Post with Id $postId not EXIST!")
//        comments += comment
//        return comment
//    }

    fun createComment(postId: Int, comment: Comment): Comment {
        var result: Int? = findById(postId)
        if (result == null) {
            val badComment: BadComment = BadComment(reason = 9)     // 9 - несуществующий postId
            badComments += badComment
            throw PostNotFoundException("Post with Id $postId not EXIST!")
        }
        if (comment.text.toLowerCase().contains("опиум")) {
            val badComment: BadComment = BadComment(reason = 4)     // 4 - коммент пропаганда наркотиков
            badComments += badComment
        }
        comments += comment
        return comment
    }

    fun reportComments(): Array<BadComment> {
        return badComments
    }

    fun clearBadComments() {
        badComments = emptyArray<BadComment>()
    }

    fun getComments(): Array<Comment> {
        return comments
    }

    fun clear() {
        posts = emptyArray()
        lastPost = 0
    }

    fun add(post: Post): Post {
        posts += post.copy(id = lastPost)
        lastPost += 1
        return posts.last()
    }

    fun update(post: Post): Boolean {
        var status: Boolean = false
        for ((index, postId) in posts.withIndex()) {
            status = postId.id == post.id
            if (status) {
                posts[index] = post.copy()
                println("new Post ${posts[index]}")
                break
            }
        }
        return status
    }

    fun getPosts(): Array<Post> {
        return posts
    }

    private fun findById(id: Int): Int? {
        for (post in posts) {
            if (post.id == id) return id
        }
        return null
    }
}

class CopyRight(
    val id: Int,
    val link: String = "source",
    val name: String = "Name",
    val type: String = "Type"
)

class Likes(
    var count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = false,
    val canPublish: Boolean = false
)

class Comments(
    val count: Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = true,
    val canClose: Boolean = true,
    val canOpen: Boolean = true
)

class Reposts(
    val count: Int = 0,
    val userReposted: Boolean = false
)

class Views {
    val count: Int = 0
}

class PostSource(
    val type: String = "VK",
    val platform: String = "android",
    val data: String = "profile_activity",
    val url: URL = URL("https://dev.vk.com/ru/reference/objects/post?ref=old_portal")
)

class Geo(
    val type: String = "",
    val coordinates: String = "",
    val place: Place?
)

class Place(
    val id: Int = 0,
    val title: String = "",
    val latitude: Int = 0,
    val longitude: Int = 0,
    val created: Int = 0,
    val icon: String = "",
    val checkins: Int = 0,
    val updated: Int = 0,
    val type: Int = 0,
    val country: Int = 0,
    val city: Int = 0,
    val address: String = ""
)

class Placeholder(
    val id: Int = 0,
    val title: String = "",
    val latitude: Int = 0,
    val longitude: Int = 0,
    val created: Int = 0,
    val icon: String = "",
    val checkins: Int = 0,
    val updated: Int = 0,
    val type: Int = 0,
    val country: Int = 0,
    val city: Int = 0,
    val address: String = ""
)

class Donut(
    val isDonut: Boolean = false,
    val paidDuration: Int = 0,
    val placeholder: Placeholder,
    val canPublishFreeCopy: Boolean = false,
    val editMode: String = "all"
)

data class Post(
    val id: Int = 0,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    val date: Long = 0L,
    val text: String = "text",
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendsOnly: Boolean = true,
    val comments: Comments?,
    val copyright: CopyRight?,
    val likes: Likes?,
    val reposts: Reposts?,
    val views: Views?,
    val postType: String = "post",
    val postSource: PostSource?,
    val geo: Geo?,
    val signerId: Int = 0,
    val copyHistory: Array<String> = emptyArray(),
    val canPin: Boolean = false,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val donut: Donut?,
    val postponedId: Int = 0,
    val attachments: Array<Attachment> = emptyArray()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (!copyHistory.contentEquals(other.copyHistory)) return false

        return true
    }

    override fun hashCode(): Int {
        return copyHistory.contentHashCode()
    }
}


fun main() {
    for (i in 0..10) {
        val copy = CopyRight(i)
        val like = Likes(i + 10)
        WallService.add(
            Post(
                likes = like, copyright = copy, donut = null,
                geo = null, postSource = null, views = null, reposts = null, comments = null
            )
        )
    }

    val copy = CopyRight(5)
    val like = Likes(10)
    val post1 = Post(
        17, ownerId = 17, createdBy = 45, likes = like, copyright = copy, donut = null,
        geo = null, postSource = null, views = null, reposts = null, comments = null
    )
    checkPost(post1)
    val post2 = Post(
        7, ownerId = 37, createdBy = 45, likes = like, copyright = copy, donut = null,
        geo = null, postSource = null, views = null, reposts = null, comments = null
    )
    checkPost(post2)

    WallService.createComment(5, Comment(replayAttachments = null, text = "производство опиума"))
    println("added bad comments ${WallService.reportComments().size}")
}

fun checkPost(post: Post) {
    when (WallService.update(post)) {
        true -> {
            println("Post successful updated! \n${post}")
        }

        false -> {
            println("Post not found!")
        }
    }
}



