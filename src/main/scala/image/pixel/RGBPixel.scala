package image.pixel


case class RGBPixel (r: Int, g: Int, b: Int) extends Pixel {
//  def this(red: Int, green: Int, blue: Int){
//    this(red, green, blue)
//  }

  override def getNormalized: RGBPixel = RGBPixel(normalize(r), normalize(g), normalize(b))

  def getRed: Int = r
  def getGreen: Int = g
  def getBlue: Int = b

  def pixelToInt(): Int = r + g + b

}
