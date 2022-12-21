package command.`export`

import command.Command
import image.{GrayscaleImage, Image}
import image.pixel.Pixel
import transformation.DefaultLinearTransformation

import java.io.{BufferedWriter, File, FileWriter}

class FileOutput (path: String) extends Command {
  override def execute(image: GrayscaleImage): GrayscaleImage = {
    val outputFile = new File(path)
    val bw = new BufferedWriter(new FileWriter(outputFile))
    val width = image.getWidth
    val height = image.getHeight

    for (i <- 0 until height){
      for (j <- 0 until width)
        bw.append(DefaultLinearTransformation.getSymbol(image.getPixel(i, j).getGrayscale))
      bw.append('\n')
    }
    bw.close()

    image
  }
}
