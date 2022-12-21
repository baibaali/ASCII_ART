package parser

import command.Command
import command.filter.flip.Flip
import command.filter.rotate.Rotate
import command.filter.{Brightness, Invert, Scale}
import image.`export`.{ConsoleOutput, FileOutput, ImageWriter}
import image.loader.fileLoader.{JPGImageLoader, PNGImageLoader}
import image.loader.ImageLoader
import image.loader.imageGenerator.RandomImageGenerator
import transformation.DefaultLinearTransformation

import scala.collection.mutable.ListBuffer
import scala.sys.exit

object ArgumentParser {

  val usage = """
Usage: run [options]
  [ --help                                  ]   Lists the supported options
    --image                 string              Path to input file.
  [ --output-file           string          ]   Path to output file.
  [ --output-console                        ]   Print output to console
  [ --rotate                int             ]   Rotation degree
  [ --invert                                ]   Inverting image
  [ --brightness            int             ]   Brightening image
  [ --flip                  x|y             ]   Flips the image on 'x' or 'y' axis
Note that --input and --output-file/--output-console is required.
  """

  def parse(args: Array[String]) : (ListBuffer[Command], ImageLoader, ListBuffer[ImageWriter]) = {

    if (args.length == 0) {
      println(usage)
      throw new Exception("No arguments were given")
    }
    var loader: ImageLoader = new RandomImageGenerator()
    val output = new ListBuffer[ImageWriter]

    var isImageSet: Boolean = false
    var isOutputSet: Boolean = false

    def parseArgs(list: ListBuffer[Command], argumentList: List[String]): (ListBuffer[Command], ImageLoader, ListBuffer[ImageWriter]) = {
      argumentList match {
        case Nil => (list, loader, output)
        case "--help" :: tail  =>
          println(usage)
          exit(0)
        case "--image" :: value :: tail =>
          val fileFormat = value.substring(value.lastIndexOf('.') + 1)
          fileFormat match {
            case "png" =>
              loader = new PNGImageLoader(value)
              isImageSet = true
            case "jpg" =>
              loader = new JPGImageLoader(value)
              isImageSet = true
            case _     =>
              throw new Exception("Unsupported file format")
          }
          parseArgs(list, tail)
        case "--image-random" :: tail =>
          loader = new RandomImageGenerator()
          isImageSet = true
          parseArgs(list, tail)
        case "--output-file" :: value :: tail =>
          output.append(new FileOutput(value))
          isOutputSet = true
          parseArgs(list, tail)
        case "--output-console" :: tail =>
          output.append(new ConsoleOutput())
          isOutputSet = true
          parseArgs(list, tail)
        case "--rotate" :: value :: tail =>
          val degree = value.toInt
          if (degree % 90 != 0)
            throw new Exception("Unsupported degree value")
          parseArgs(list.append(new Rotate(value.toInt)), tail)
        case "--invert" :: tail =>
          parseArgs(list.append(new Invert()), tail)
        case "--brightness" :: value :: tail =>
          parseArgs(list.append(new Brightness(value.toInt)), tail)
        case "--flip" :: value :: tail =>
          if (value != "x" && value != "y")
            throw new Exception("Wrong value for option --flip")
          parseArgs(list.append(new Flip(value)), tail)
        case "--custom-table" :: value :: tail =>
          DefaultLinearTransformation.setSymbols(value)
          parseArgs(list, tail)
        case unknown :: _ =>
          throw new Exception("Unknown option " + unknown + "\n" + "Try run with --help to list the supported options")
      }
    }

    val (first, second, third) = parseArgs(ListBuffer[Command](), args.toList)

    if (!isImageSet)
      throw new Exception("Input image source must be specified")

    if (!isOutputSet)
      throw new Exception("Output method must be specified")

    (first, second, third)
  }

}
