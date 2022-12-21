package command.filter

import command.Command
import image.Image
import image.pixel.Pixel

class Brightness (brightness: Int) extends Command {
  override def execute(image: Image[Pixel]): Unit = ???
}
