package command.filter

import command.Command
import image.Image
import image.pixel.Pixel

class Scale (scaleFactor: Double) extends Command {
  override def execute(image: Image[Pixel]): Unit = ???
}
