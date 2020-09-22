package vaida.dryzaite.begoodapp.data.remote.responses

data class ImageResponse(
    val hits: List<ImageResult>,
    val total: Int,
    val totalHits: Int
)