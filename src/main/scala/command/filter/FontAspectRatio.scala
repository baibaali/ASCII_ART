package command.filter

import command.Command
import image.Image
import image.pixel.Pixel

class FontAspectRatio (ratio: String) extends Command {
  override def execute(image: Image[Pixel]): Unit = ???
}
