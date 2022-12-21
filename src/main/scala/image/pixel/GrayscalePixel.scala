package image.pixel

case class GrayscalePixel(grayscaleFactor: Int) extends Pixel {
  override def getNormalized: GrayscalePixel = GrayscalePixel(normalize(grayscaleFactor))
}
