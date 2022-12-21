package command.filter.flip

import command.Command
import image.GrayscaleImage
import image.pixel.GrayscalePixel

import scala.collection.mutable.ArrayBuffer

case class FlipY() extends Command {
  override def execute(image: GrayscaleImage): GrayscaleImage = {
    val result = new GrayscaleImage

    val height = image.getHeight
    val width = image.getWidth

    for (x <- 0 until height) {
      val row = new ArrayBuffer[GrayscalePixel]
      for (y <- 0 until width) {
        row.append(image.getPixel(height - x - 1, y))
      }
      result.appendRow(row)
    }

    result
  }
}
