package image.loader.fileLoader

import image.RGBAImage
import image.loader.ImageLoader
import image.pixel.{PixelCollection, RGBAPixel}

import java.awt.image.BufferedImage
import java.io.{File, IOException}
import javax.imageio.ImageIO
import scala.sys.exit

class JPGImageLoader (path: String) extends ImageLoader {
  override def load(): RGBAImage = {
    try {
      val image = ImageIO.read(new File(path))

      if (image.getWidth <= 0 || image.getHeight <= 0)
        throw new Exception("Unsupported image resolution. Must be non-zero")

      try {
        getPixels(image)
      } catch {
        case e: Exception =>
          println(e.getLocalizedMessage)
          exit(1)
      }

    } catch {
      case e: IOException =>
        println(e.getLocalizedMessage)
        exit(1)
    }
  }
}
