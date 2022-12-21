package image.loader

import image.pixel.Pixel
import image.{Image}

trait Loader {
  def load(): Object
}
