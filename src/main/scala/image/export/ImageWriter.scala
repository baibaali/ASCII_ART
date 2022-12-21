package image.`export`

import image.GrayscaleImage

abstract class ImageWriter extends Writer {
  def save(image: GrayscaleImage): Unit
}
