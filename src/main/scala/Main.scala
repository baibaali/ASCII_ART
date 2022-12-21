
import convertor.ImageConvertor
import image.RGBAImage
import image.pixel.RGBAPixel
import parser.ArgumentParser

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object Main extends App {
  val (commands, loader) = ArgumentParser.parse(args)

  val image = loader.load()
  val imageConvertor = new ImageConvertor[RGBAPixel]

  val width = image.getWidth
  val height = image.getHeight

  val out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

  for (x <- 0 until width)
    for (y <- 0 until height) {
      out.setRGB(x, y, image.getPixel(y, x).pixelToInt)
    }

  ImageIO.write(out, "png", new File("test.png"))

  var grayscaled = imageConvertor.RGBaToGrayscale(image)

  val width_g = grayscaled.getWidth
  val height_g = grayscaled.getHeight

  val out_g = new BufferedImage(width_g, height_g, BufferedImage.TYPE_INT_RGB)

  for (x <- 0 until width_g)
    for (y <- 0 until height_g) {
      out_g.setRGB(x, y, grayscaled.getPixel(y, x).pixelToInt)
    }

  ImageIO.write(out_g, "png", new File("test_g.png"))

  for (cmd <- commands) {
    grayscaled = cmd.execute(grayscaled)
  }

}