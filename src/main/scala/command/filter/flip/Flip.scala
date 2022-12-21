package command.filter.flip

import command.Command
import image.GrayscaleImage

class Flip (axis: String) extends Command {
  override def execute(image: GrayscaleImage): GrayscaleImage = {
    axis match {
      case "x" =>
        new FlipX().execute(image)
      case "y" =>
        new FlipY().execute(image)
      case _ =>
        throw new Exception("Unknown axis")
    }
  }
}
