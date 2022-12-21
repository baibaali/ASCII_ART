package command.filter

import command.Command
import image.{GrayscaleImage, Image}
import image.pixel.Pixel

class Scale (scaleFactor: Double) extends Command {
  override def execute(image: GrayscaleImage): GrayscaleImage = ???
}
