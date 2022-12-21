package image.pixel

case class GrayscalePixel(grayscaleFactor: Int) extends Pixel {
  def this(grayscale: Int) = {
    this(grayscale)
  }

  override def getNormalized: GrayscalePixel = GrayscalePixel(normalize(grayscaleFactor))
}
