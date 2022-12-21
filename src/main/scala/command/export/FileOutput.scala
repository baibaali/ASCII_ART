package command.`export`

import command.Command
import image.Image
import image.pixel.Pixel

class FileOutput (path: String) extends Command {
  override def execute(image: Image[Pixel]): Unit = ???
}
