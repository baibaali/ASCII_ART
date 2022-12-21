package image.pixel

import scala.collection.mutable.ArrayBuffer

class PixelCollection[P <: Pixel] (val pixels: ArrayBuffer[ArrayBuffer[P]]) {
  def this() = {
    this(ArrayBuffer[ArrayBuffer[P]]())
  }

  def getWidth:  Int = if (pixels.isEmpty) 0 else pixels(0).length
  def getHeight: Int = pixels.length

  def getPixel(i: Int, j: Int): P = {
    if (i < 0 || i >= getHeight || j < 0 || j >= getWidth)
      throw new Exception("Out of range")
    pixels(i)(j)
  }

  def appendRow(row: ArrayBuffer[P]): Unit = {
    pixels.append(row.clone())
  }
}
