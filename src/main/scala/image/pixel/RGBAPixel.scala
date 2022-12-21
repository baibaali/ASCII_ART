package image.pixel

case class RGBAPixel(alphaChannel: Int, r: Int, g: Int, b: Int) extends Pixel {
  def this(alpha: Int, red: Int, green: Int, blue: Int) {
    this(alpha, red, green, blue)
  }

  override def getNormalized: RGBAPixel = RGBAPixel(normalize(alphaChannel), normalize(r), normalize(g), normalize(b))
}
