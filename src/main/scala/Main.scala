
import convertor.ImageConvertor
import image.pixel.RGBAPixel
import parser.ArgumentParser

import scala.sys.exit

object Main extends App {

  try {
    val (commands, loader, output) = ArgumentParser.parse(args)

    val image = loader.load()
    val imageConvertor = new ImageConvertor[RGBAPixel]

    var grayscaled = imageConvertor.RGBaToGrayscale(image)

    for (cmd <- commands)
      grayscaled = cmd.execute(grayscaled)

    for (out <- output)
      out.save(grayscaled)
  } catch {
    case e: Exception =>
      println(e.getLocalizedMessage)
      exit(1)
  }
}