package command.filter.flip

import command.Command
import image.GrayscaleImage

class Flip (axis: String) extends Command {
  override def execute(image: GrayscaleImage): GrayscaleImage = {
    axis match {
      case "x" =>
        FlipX().execute(image)
      case "y" =>
        FlipY().execute(image)
    }
  }
}
