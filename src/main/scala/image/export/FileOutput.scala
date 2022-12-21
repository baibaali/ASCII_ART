package image.`export`

import image.GrayscaleImage
import transformation.DefaultLinearTransformation

import java.io.{BufferedWriter, File, FileWriter}

class FileOutput (path: String) extends ImageWriter {
  override def save(image: GrayscaleImage): Unit = {
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
  }
}
