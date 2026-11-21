class VideoStreaming(
    uri: String,
    override var duracionSegundos: Int,
    val calidad: String
) : ArchivoMultimedia(uri, duracionSegundos), ControlReproduccion {
    override fun reproducir() {
        println("Streaming de vídeo en $calidad ($uri)...")
    }

    override fun pausar() {
        println("Vídeo pausado. Buffer conservado.")
    }
}

fun main() {
    val cancion = PistaAudio("https://music.com/song1.mp3", 215, "Periko y los borricos")
    val podcast = PistaAudio("file://local/podcast.mp3") // usa constructor secundario
    val video = VideoStreaming("https://video.com/movie.mp4", 1200, "1080p")
    val elementos: List<ControlReproduccion> = listOf(cancion, podcast, video)
    for (item in elementos) {
        println(item.toString())
        item.reproducir()
        item.pausar()
        item.detener()
        println("---")
    }
}
