
import convertor.ImageConvertor
import image.pixel.RGBAPixel
import parser.ArgumentParser

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object Main extends App {
  val (commands, loader, output) = ArgumentParser.parse(args)

  val image = loader.load()
  val imageConvertor = new ImageConvertor[RGBAPixel]

  var grayscaled = imageConvertor.RGBaToGrayscale(image)

  for (cmd <- commands)
    grayscaled = cmd.execute(grayscaled)

  for (out <- output)
    out.save(grayscaled)

}