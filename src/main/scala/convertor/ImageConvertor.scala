package convertor

import image.{GrayscaleImage, Image}
import image.pixel.{GrayscalePixel, Pixel}

import scala.collection.mutable.ArrayBuffer

class ImageConvertor[P <: Pixel] extends Convertor {

  def RGBaToGrayscale(image: Image[P]): GrayscaleImage = {
    val width  = image.getWidth
    val height = image.getHeight

    val grayscaleImage = new GrayscaleImage()

    for (x <- 0 until height){
      val row = new ArrayBuffer[GrayscalePixel]()
      for (y <- 0 until width) {
        row.append(GrayscalePixel(getGrayscaleFactor(
          image.getPixel(x, y).getRed,
          image.getPixel(x, y).getGreen,
          image.getPixel(x, y).getBlue
        )).getNormalized)
      }
      grayscaleImage.appendRow(row)
    }
    grayscaleImage
  }

  def getGrayscaleFactor(red: Int, green: Int, blue: Int): Int = {
    ((0.3 * red) + (0.59 * green) + (0.11 * blue)).toInt
  }

}
