package command

import image.{GrayscaleImage, Image}
import image.pixel.Pixel

trait Command {
  def execute(image: GrayscaleImage): GrayscaleImage
}
