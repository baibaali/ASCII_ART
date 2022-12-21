package image.`export`

import image.GrayscaleImage
import transformation.DefaultLinearTransformation


class ConsoleOutput extends ImageWriter {
   override def save(image: GrayscaleImage): Unit = {
    val width = image.getWidth
    val height = image.getHeight

    for (i <- 0 until height){
      for (j <- 0 until width)
        print(DefaultLinearTransformation.getSymbol(image.getPixel(i, j).getGrayscale))
      println()
    }
   }
}
