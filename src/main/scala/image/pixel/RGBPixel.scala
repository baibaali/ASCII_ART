package image.pixel


case class RGBPixel (r: Int, g: Int, b: Int) extends Pixel {
  def this(red: Int, green: Int, blue: Int){
    this(red, green, blue)
  }

  override def getNormalized: RGBPixel = RGBPixel(normalize(r), normalize(g), normalize(b))


}
