package ru.netology

import java.net.URL

abstract class Attachment {
    abstract val typeAttach: String
}


data class Audio(
    val id: Int = 0,
    val ownerId: Int = 0,
    val artist: String = "Artist",
    val title: String = "Title Document",
    val duration: Int = 0,
    val url: URL?,
    val lyricsId: Int = 0,
    val albumId: Int = 0,
    val genreId: Int = 0,
    val date: Int = 0,
    val noSearch: Boolean = true,
    val isHq: Boolean = true
)

data class Video(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "Title Document",
    val description: String = "Video description",
    val duration: Int = 0,
    val link: String = "link Video",
    val image: URL?,
    val imageMedium: String = "imageMedium",
    val date: Int = 0,
    val player: String = "Video player"
)

data class Photo(
    val id: Int = 0,
    val ownerId: Int = 0,
    val photo130: URL?,
    val photo_604: URL?
)

data class Document(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "Title Document",
    val size: Int = 0,
    val ext: String = "ext",
    val url: URL?,
    val date: Int = 0,
    val typeDoc: Int = 0
)

data class Graffiti(
    val id: Int = 0,
    val ownerId: Int = 0,
    val photo_130: URL?,
    val photo_604: URL?
)

data class PhotoAttachment(
    override val typeAttach: String = "photo",
    val attach: Photo?
) : Attachment()


data class AudioAttachment(
    override val typeAttach: String = "audio",
    val attach: Audio?
) : Attachment()

data class VideoAttachment(
    override val typeAttach: String = "video",
    val attach: Video?
) : Attachment()

data class DocumentAttachment(
    override val typeAttach: String = "doc",
    val attach: Document?
) : Attachment()

data class GraffitiAttachment(
    override val typeAttach: String = "graffiti",
    val attach: Graffiti?
) : Attachment()
