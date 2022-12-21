package image.pixel

import scala.collection.mutable.ArrayBuffer

class PixelCollection[P <: Pixel] (val pixels: ArrayBuffer[ArrayBuffer[P]]) {
  def this() = {
    this(ArrayBuffer[ArrayBuffer[P]])
  }

  def getWidth:  Int = if (pixels.isEmpty) 0 else pixels(0).length
  def getHeight: Int = pixels.length

  def appendRow(row: ArrayBuffer[P]): Unit = {
    pixels.append(row)
  }
}
