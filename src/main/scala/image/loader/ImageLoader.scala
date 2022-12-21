package image.loader

import image.{Image, RGBAImage, RGBImage}
import image.pixel.{Pixel, RGBAPixel, RGBPixel}

import java.awt.image.BufferedImage
import scala.collection.mutable.ArrayBuffer
import java.awt.image.DataBufferByte


abstract class ImageLoader extends Loader {

  /*
   * The faster way to create a 2D array of image pixels
   */
  def getPixels(image: BufferedImage): RGBAImage = {
    val pixels = image.getRaster.getDataBuffer.asInstanceOf[DataBufferByte].getData
    val width = image.getWidth
    val hasAlphaChannel = image.getAlphaRaster != null

    val result = new RGBAImage
    val temp_row = new ArrayBuffer[RGBAPixel]()

    var pixelLength = 4
    var step = 3
    var pixel = 0
    var row = 0
    var column = 0

    if (!hasAlphaChannel) {
      pixelLength -= 1
      step -= 1
    }

    while (pixel + step < pixels.length) {
      temp_row.append( {
        step match {
          case 3 => pixelWithAlphaChannel(pixel, pixels)
          case 2 => pixelWithoutAlphaChannel(pixel, pixels)
        }
      })
      column = (column + 1) % width
      row   += (column == 0)

      if (column == 0){
        result.appendRow(temp_row)
        temp_row.clear()
      }

      pixel += pixelLength
    }

    result
  }

  def pixelWithAlphaChannel(pixel: Int, pixels: Array[Byte]): RGBAPixel = {

    val alpha = ((pixels(pixel).toInt     & 0xff) << 24) // alpha
    val red   = ((pixels(pixel + 3).toInt & 0xff) << 16) // red
    val green = ((pixels(pixel + 2).toInt & 0xff) << 8 ) // green
    val blue  = ( pixels(pixel + 1).toInt & 0xff)        // blue

    RGBAPixel(alpha, red, green, blue)
  }

  def pixelWithoutAlphaChannel(pixel: Int, pixels: Array[Byte]): RGBAPixel = {

    val red   = ((pixels(pixel + 2).toInt & 0xff) << 16) // red
    val green = ((pixels(pixel + 1).toInt & 0xff) << 8 ) // green
    val blue  = (pixels(pixel).toInt      & 0xff)        // blue

    RGBAPixel(0, red, green, blue)
  }
}
