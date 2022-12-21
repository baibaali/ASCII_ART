
import image.RGBAImage
import parser.ArgumentParser

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object Main extends App {
  val (commands, loader) = ArgumentParser.parse(args)

  val image = loader.load()

  val width = image.getWidth
  val height = image.getHeight

  val out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

  for (x <- 0 until width)
    for (y <- 0 until height) {
      out.setRGB(x, y, image.getPixel(y, x).pixelToInt & 0xffffff)
    }

  ImageIO.write(out, "png", new File("test.png"))
}