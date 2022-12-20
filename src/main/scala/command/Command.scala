package command

import image.Image

trait Command {
  def execute(image: Image): Unit
}
