
import org.scalatest.FunSuite
import command.Command
import convertor.ImageConvertor
import image.pixel.RGBAPixel
import parser.ArgumentParser

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

class MainTest extends FunSuite {
  test("Main class test"){

  val args: ArrayBuffer[String] = new ArrayBuffer[String]()

    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--rotate")
      .append("-90")
      .append("--flip")
      .append("y")
      .append("--output-file")
      .append("src/test/resources/output/test_rgb.txt")

    val (commands, loader, output) = ArgumentParser.parse(args.toArray)

    val image = loader.load()
    val convertor = new ImageConvertor[RGBAPixel]

    var grayscaled_image = convertor.RGBaToGrayscale(image)

    for (cmd <- commands)
      grayscaled_image = cmd.execute(grayscaled_image)

    for (out <- output)
      out.save(grayscaled_image)

    val bf = Source.fromFile("src/test/resources/output/test_rgb.txt")

    val lines = bf.getLines.toList

    assert(lines.head == "#=")
    assert(lines(1)   == "%+")

    bf.close()

  }
}
