package command.filter.rotate

import command.Command
import image.GrayscaleImage
import image.pixel.GrayscalePixel

import scala.collection.mutable.ArrayBuffer

class Rotate90 extends Command{
  override def execute(image: GrayscaleImage): GrayscaleImage = {

    val result = new GrayscaleImage

    val height = image.getHeight
    val width  = image.getWidth

    var pixels = new ArrayBuffer[ArrayBuffer[GrayscalePixel]]()

    for (i <- 0 until height)
      pixels.append(image.getRow(i))

    for (i <- 0 until height){
      for (j <- i until width) {
        val temp = pixels(i)(j)
        pixels(i)(j) = pixels(j)(i)
        pixels(j)(i) = temp
      }
    }

    for (i <- 0 until height)
      result.appendRow(pixels(i).reverse)

    result
  }
}
