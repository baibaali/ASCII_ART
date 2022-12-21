package command.`export`

import command.Command
import image.{GrayscaleImage, Image}
import image.pixel.Pixel
import transformation.DefaultLinearTransformation

import java.io.{BufferedWriter, File, FileWriter}

class ConsoleOutput extends Command {
  override def execute(image: GrayscaleImage): Unit = {
    val width = image.getWidth
    val height = image.getHeight

    for (i <- 0 until height){
      for (j <- 0 until width)
        print(DefaultLinearTransformation.getSymbol(image.getPixel(i, j).getGrayscale))
      println()
    }
  }
}
