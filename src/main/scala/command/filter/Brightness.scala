package command.filter

import command.Command
import image.{GrayscaleImage, Image}
import image.pixel.{GrayscalePixel, Pixel}

import scala.collection.mutable.ArrayBuffer

class Brightness (brightness: Int) extends Command {
  override def execute(image: GrayscaleImage): GrayscaleImage = {
    val width = image.getWidth
    val height = image.getHeight

    val result = new GrayscaleImage

    for (i <- 0 until height){
      val row = new ArrayBuffer[GrayscalePixel]
      for (j <- 0 until width)
        row.append(GrayscalePixel(image.getPixel(i, j).getGrayscale + (brightness % 256)).getNormalized)
      result.appendRow(row)
    }

    result
  }
}
