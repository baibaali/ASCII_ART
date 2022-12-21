package image.pixel

case class GrayscalePixel(grayscaleFactor: Int) extends Pixel {
  override def getNormalized: GrayscalePixel = GrayscalePixel(normalize(grayscaleFactor))

  override def getRed: Int = grayscaleFactor
  override def getGreen: Int = grayscaleFactor
  override def getBlue: Int = grayscaleFactor
  def getGrayscale: Int = grayscaleFactor

  override def pixelToInt: Int = (0 << 24) | (grayscaleFactor << 16) | (grayscaleFactor << 8) | grayscaleFactor
}
