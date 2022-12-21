package command.filter

import command.Command
import image.GrayscaleImage

import scala.sys.exit

class Rotate (degree: Int) extends Command {
  override def execute(image: GrayscaleImage): GrayscaleImage = {

   val rotate: Rotate90 = new Rotate90

   degree match {
     case 0 | 360 =>
       image
     case 90 | -270  =>
       rotate.execute(image)
     case -180 | 180 =>
       rotate.execute(rotate.execute(image))
     case -90 | 270 =>
       rotate.execute(rotate.execute(rotate.execute(image)))
     case _ =>
       println("Unsupported degree of rotation")
       exit(1)
   }

  }
}
