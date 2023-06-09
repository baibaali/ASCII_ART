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
    val height = image.getHeight
    val hasAlphaChannel = image.getAlphaRaster != null

    val result = new RGBAImage

    var pixelLength = 4
    var step = 3
    var pixel = 0
    var row = 0
    var column = 0

    if (!hasAlphaChannel) {
      pixelLength -= 1
      step -= 1
    }

    var temp_row = new ArrayBuffer[RGBAPixel]()

    while (pixel + step < pixels.length) {
      temp_row.append( {
        step match {
          case 3 => pixelWithAlphaChannel(pixel, pixels)
          case 2 => pixelWithoutAlphaChannel(pixel, pixels)
        }
      })
      column = (column + 1) % width

      if (column == 0){
        row += 1
        if (temp_row.length != width)
          throw new Exception("Something went wrong, while reading the image.")
        result.appendRow(temp_row)
        temp_row = new ArrayBuffer[RGBAPixel]()
      }

      pixel += pixelLength
    }

    result
  }

  def pixelWithAlphaChannel(pixel: Int, pixels: Array[Byte]): RGBAPixel = {

    val alpha = (pixels(pixel).toInt     & 0xff)      // alpha
    val red   = (pixels(pixel + 3).toInt & 0xff)      // red
    val green = (pixels(pixel + 2).toInt & 0x00ff)    // green
    val blue  = ( pixels(pixel + 1).toInt & 0x0000ff) // blue

    RGBAPixel(alpha, red, green, blue)
  }

  def pixelWithoutAlphaChannel(pixel: Int, pixels: Array[Byte]): RGBAPixel = {

    val red   = (pixels(pixel + 2).toInt & 0xff)           // red
    val green = (pixels(pixel + 1).toInt & 0x00ff)         // green
    val blue  = (pixels(pixel).toInt      & 0x0000ff)       // blue

    RGBAPixel(0, red, green, blue)
  }
}
