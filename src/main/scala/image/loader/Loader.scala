package image.loader

import image.RGBAImage

trait Loader {
  def load(): RGBAImage
}
