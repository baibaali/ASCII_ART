package command

import image.Image
import image.pixel.Pixel

trait Command {
  def execute(image: Image[Pixel]): Unit
}
