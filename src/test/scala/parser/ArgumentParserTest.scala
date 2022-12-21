package parser

import command.filter.{Brightness, Invert}
import command.filter.flip.{Flip, FlipX, FlipY}
import command.filter.rotate.Rotate
import image.loader.fileLoader.{JPGImageLoader, PNGImageLoader}
import image.loader.imageGenerator.RandomImageGenerator
import org.scalatest.FunSuite
import transformation.DefaultLinearTransformation

import java.io.File
import scala.collection.mutable.ArrayBuffer

class ArgumentParserTest extends FunSuite {

  test("No arguments test") {
    assertThrows[Exception](ArgumentParser.parse(new Array[String](0)))
  }

  test("Random image generator test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image-random")
      .append("--output-file")
      .append("src/test/resources/output/random_image_test.txt")

    val (commands, loader, output) = ArgumentParser.parse(args.toArray)

    assert(commands.isEmpty)
    assert(loader.isInstanceOf[RandomImageGenerator])
    assert(output.length == 1)

//    val outputFile = new File("src/test/resources/output/random_image_test.txt")
//    assert(outputFile.exists() && outputFile.length() != 0)
    //outputFile.delete()
  }

  test("Reading PNG file test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--output-console")


    val (commands, loader, output) = ArgumentParser.parse(args.toArray)
    assert(commands.isEmpty)
    assert(loader.isInstanceOf[PNGImageLoader])
  }

  test("Reading JPG file test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.jpg")
      .append("--output-console")


    val (commands, loader, output) = ArgumentParser.parse(args.toArray)
    assert(commands.isEmpty)
    assert(loader.isInstanceOf[JPGImageLoader])
  }

  test("Invalid image format test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.ttf")
      .append("--output-console")

    assertThrows[Exception](ArgumentParser.parse(args.toArray))
  }

  test("Unknown option test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--file")
      .append("src/test/resources/input/test_rgb.ttf")
      .append("--output-console")

    assertThrows[Exception](ArgumentParser.parse(args.toArray))
  }

  test("Invert filter test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--invert")
      .append("--output-console")

    val (commands, loader, output) = ArgumentParser.parse(args.toArray)

    assert(commands.head.isInstanceOf[Invert])
  }

  test("Flip by x-axis filter test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--flip")
      .append("x")
      .append("--output-console")

    val (commands, loader, output) = ArgumentParser.parse(args.toArray)

    assert(commands.head.isInstanceOf[Flip])
  }

  test("Flip by y-axis filter test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--flip")
      .append("y")
      .append("--output-console")


    val (commands, loader, output) = ArgumentParser.parse(args.toArray)

    assert(commands.head.isInstanceOf[Flip])
  }

  test("Flip filter with wrong value test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--flip")
      .append("z")
      .append("--output-console")

    assertThrows[Exception](ArgumentParser.parse(args.toArray))
  }

  test("Brightness filter test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--brightness")
      .append("20")
      .append("--output-console")

    val (commands, loader, output) = ArgumentParser.parse(args.toArray)
    assert(commands.head.isInstanceOf[Brightness])
  }

  test("Brightness filter with wrong value test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--brightness")
      .append("20dvacet")
      .append("--output-console")

    assertThrows[Exception](ArgumentParser.parse(args.toArray))
  }

  test("Rotate 90 filter test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--rotate")
      .append("90")
      .append("--output-console")

    val (commands, loader, output) = ArgumentParser.parse(args.toArray)
    assert(commands.head.isInstanceOf[Rotate])
  }

  test("Rotate +90 filter test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--rotate")
      .append("+90")
      .append("--output-console")

    val (commands, loader, output) = ArgumentParser.parse(args.toArray)
    assert(commands.head.isInstanceOf[Rotate])
  }

  test("Rotate -90 filter test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--rotate")
      .append("-90")
      .append("--output-console")

    val (commands, loader, output) = ArgumentParser.parse(args.toArray)
    assert(commands.head.isInstanceOf[Rotate])
  }

  test("Rotate +180 filter test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--rotate")
      .append("+180")
      .append("--output-console")

    val (commands, loader, output) = ArgumentParser.parse(args.toArray)
    assert(commands.head.isInstanceOf[Rotate])
  }

  test("Rotate -180 filter test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--rotate")
      .append("-180")
      .append("--output-console")

    val (commands, loader, output) = ArgumentParser.parse(args.toArray)
    assert(commands.head.isInstanceOf[Rotate])
  }

  test("Rotate +270 filter test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--rotate")
      .append("+270")
      .append("--output-console")

    val (commands, loader, output) = ArgumentParser.parse(args.toArray)
    assert(commands.head.isInstanceOf[Rotate])
  }

  test("Rotate -270 filter test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--rotate")
      .append("-270")
      .append("--output-console")

    val (commands, loader, output) = ArgumentParser.parse(args.toArray)
    assert(commands.head.isInstanceOf[Rotate])
  }

  test("Rotate 270 filter test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--rotate")
      .append("270")
      .append("--output-console")

    val (commands, loader, output) = ArgumentParser.parse(args.toArray)
    assert(commands.head.isInstanceOf[Rotate])
  }

  test("Rotate +360 filter test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--rotate")
      .append("+360")
      .append("--output-console")

    val (commands, loader, output) = ArgumentParser.parse(args.toArray)
    assert(commands.head.isInstanceOf[Rotate])
  }

  test("Rotate -360 filter test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--rotate")
      .append("-360")
      .append("--output-console")

    val (commands, loader, output) = ArgumentParser.parse(args.toArray)
    assert(commands.head.isInstanceOf[Rotate])
  }

  test("Rotate 360 filter test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--rotate")
      .append("360")
      .append("--output-console")

    val (commands, loader, output) = ArgumentParser.parse(args.toArray)
    assert(commands.head.isInstanceOf[Rotate])
  }

  test("Rotate 0 filter test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--rotate")
      .append("0")
      .append("--output-console")

    val (commands, loader, output) = ArgumentParser.parse(args.toArray)
    assert(commands.head.isInstanceOf[Rotate])
  }

  test("Rotate filter with unsupported degree value test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--rotate")
      .append("123")
      .append("--output-console")

    assertThrows[Exception](ArgumentParser.parse(args.toArray))
  }

  test("Rotate filter with invalid degree value test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--rotate")
      .append("hi")
      .append("--output-console")

    assertThrows[Exception](ArgumentParser.parse(args.toArray))
  }

  test("Custom linear table test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--custom-table")
      .append("h53p&!|2gD")
      .append("--output-console")

    val (commands, loader, output) = ArgumentParser.parse(args.toArray)
    assert(DefaultLinearTransformation.symbols == "h53p&!|2gD")
    assert(DefaultLinearTransformation.symbolsCount == "h53p&!|2gD".length)
  }

  test("Custom linear table without value test") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--custom-table")

    assertThrows[Exception](ArgumentParser.parse(args.toArray))
  }

  test("--image option without path") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")

    assertThrows[Exception](ArgumentParser.parse(args.toArray))
  }

  test("--output-file option without path") {
    val args = new ArrayBuffer[String]()
    args
      .append("--image")
      .append("src/test/resources/input/test_rgb.png")
      .append("--output-file")

    assertThrows[Exception](ArgumentParser.parse(args.toArray))
  }
}
