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


//    for (i <- 0 until height){
//      val temp_row = new ArrayBuffer[RGBAPixel]()
//      for (j <- 0 until width){
//        temp_row.append(RGBAPixel(
//          getAlpha(image.getRGB(j, i)),
//          getRed(image.getRGB(j, i)),
//          getGreen(image.getRGB(j, i)),
//          getBlue(image.getRGB(j, i))
//        ))
//      }
//      result.appendRow(temp_row)
//    }

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

  def getAlpha(pixel: Int): Int = (pixel >> 24) & 0xff
  def getRed(pixel: Int): Int = (pixel >> 16) & 0xff
  def getGreen(pixel: Int): Int = (pixel >> 8) & 0xff
  def getBlue(pixel: Int): Int = pixel & 0xff

  def pixelWithAlphaChannel(pixel: Int, pixels: Array[Byte]): RGBAPixel = {

    val alpha = ((pixels(pixel).toInt     & 0xff)) //>> 24) // alpha
    val red   = ((pixels(pixel + 3).toInt & 0xff)) //>> 16) // red
    val green = ((pixels(pixel + 2).toInt & 0x00ff)) //>> 8 ) // green
    val blue  = ( pixels(pixel + 1).toInt & 0x0000ff)        // blue

    RGBAPixel(alpha, red, green, blue)
  }

  def pixelWithoutAlphaChannel(pixel: Int, pixels: Array[Byte]): RGBAPixel = {

    val red   = ((pixels(pixel + 2).toInt & 0xff))// >> 16) // red
    val green = ((pixels(pixel + 1).toInt & 0x00ff))// >> 8 ) // green
    val blue  = (pixels(pixel).toInt      & 0x0000ff)        // blue

    RGBAPixel(0, red, green, blue)
  }
}
