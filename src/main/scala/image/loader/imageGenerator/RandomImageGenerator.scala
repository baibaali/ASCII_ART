package image.loader.imageGenerator

import image.{RGBAImage, RGBImage}
import image.loader.ImageLoader
import image.pixel.{RGBAPixel, RGBPixel}

import scala.collection.mutable.ArrayBuffer

class RandomImageGenerator extends ImageLoader {
  override def load(): RGBAImage = {

    val rand   = scala.util.Random
    val width  = rand.nextInt(1000) + 100
    val height = rand.nextInt(1000) + 100

    val image = new RGBAImage()

    for (x <- 0 until height) {
      val row = new ArrayBuffer[RGBAPixel]()
      for (y <- 0 until width) {
        row.append(
          RGBAPixel(
            0,
            rand.nextInt(256),
            rand.nextInt(58),
            rand.nextInt(128)
          ))
      }
      image.appendRow(row)
    }

    image
  }
}
