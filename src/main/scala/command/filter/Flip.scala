package command.filter

import command.Command
import image.{GrayscaleImage, Image}
import image.pixel.Pixel

class Flip (axis: String) extends Command {
  override def execute(image: GrayscaleImage): Unit = ???
}
