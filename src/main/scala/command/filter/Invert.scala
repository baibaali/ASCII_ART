package command.filter

import command.Command
import image.{GrayscaleImage, Image}
import image.pixel.{GrayscalePixel, Pixel}

import scala.collection.mutable.ArrayBuffer

class Invert extends Command {
  override def execute(image: GrayscaleImage): GrayscaleImage = {
    val width = image.getWidth
    val height = image.getHeight

    val result = new GrayscaleImage

    for (i <- 0 until height){
      val row = new ArrayBuffer[GrayscalePixel]
      for (j <- 0 until width)
        row.append(GrayscalePixel(255 - image.getPixel(i, j).getGrayscale).getNormalized)
      result.appendRow(row)
    }

    result
  }
}
