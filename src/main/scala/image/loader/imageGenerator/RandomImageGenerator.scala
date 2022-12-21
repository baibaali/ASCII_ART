package image.loader.imageGenerator

import image.RGBImage
import image.loader.ImageLoader
import image.pixel.RGBPixel

import scala.collection.mutable.ArrayBuffer

class RandomImageGenerator extends ImageLoader {
  override def load(): RGBImage = {

    val rand   = scala.util.Random
    val width  = rand.nextInt(1000) + 1
    val height = rand.nextInt(1000) + 1

    val image = new RGBImage()

    for (_ <- 0 until height) {
      val row = new ArrayBuffer[RGBPixel]()
      for (_ <- 0 until width) {
        row.append(
          RGBPixel(
            rand.nextInt(256),
            rand.nextInt(256),
            rand.nextInt(256)
          ))
      }
    }

    image
  }
}
