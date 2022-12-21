package image.pixel

trait Pixel {

  def getNormalized: Pixel

  def normalize(value: Int): Int = {

    if (value < 0  ) return 0
    if (value > 255) return 255

    value
  }
}
