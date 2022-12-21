package parser

import command.Command
import command.`export`.{ConsoleOutput, FileOutput}
import command.filter.{Brightness, Flip, FontAspectRatio, Invert, Rotate, Scale}
import image.loader.fileLoader.{JPGImageLoader, PNGImageLoader}
import image.loader.ImageLoader
import image.loader.imageGenerator.RandomImageGenerator
import image.pixel.GrayscalePixel

import scala.collection.mutable.ListBuffer
import scala.sys.exit

object ArgumentParser {

  val usage = """
Usage: run [options]
  [ --help                                  ]   Lists the supported options
    --image                 string              Path to input file.
  [ --output-file           string=/dev/null]   Path to output file.
  [ --output-console                        ]   Print output to console
  [ --rotate                int             ]   Rotation degree
  [ --scale                 float           ]   Scale factor
  [ --invert                                ]   Inverting image
  [ --brightness            int             ]   Brightening image
  [ --flip                  x|y             ]   Flips the image on 'x' or 'y' axis
  [ --font-aspect-ration    int:int         ]   Changes the aspect ratio of the output image according to a font’s aspect ratio

Note that --input is required.
  """

  def parse(args: Array[String]) : (ListBuffer[Command], ImageLoader) = {

    if (args.length == 0)
      println(usage)

    var loader: ImageLoader = new RandomImageGenerator()

    //TODO: convert to int/double properly
    def parseArgs(list: ListBuffer[Command], argumentList: List[String]): (ListBuffer[Command], ImageLoader) = {
      argumentList match {
        case Nil => (list, loader)
        case "--help" :: tail  =>
          println(usage)
          exit(0)
        case "--image" :: value :: tail =>
          val fileFormat = value.substring(value.lastIndexOf('.') + 1)
          fileFormat match {
            case "png" => loader = new PNGImageLoader(value)
            case "jpg" => loader = new JPGImageLoader(value)
            case _     =>
              print("Unsupported file format")
              exit(1)
          }
          parseArgs(list, tail)
        case "--image-random" :: tail =>
          loader = new RandomImageGenerator()
          parseArgs(list, tail)
        case "--output-file" :: value :: tail =>
          parseArgs(list.appended(new FileOutput(value)), tail)
        case "--output-console" :: tail =>
          parseArgs(list.appended(new ConsoleOutput()), tail)
        case "--rotate" :: value :: tail =>
          parseArgs(list.appended(new Rotate(value.toInt)), tail)
        case "--scale" :: value :: tail =>
          parseArgs(list.appended(new Scale(value.toDouble)), tail)
        case "--invert" :: tail =>
          parseArgs(list.appended(new Invert()), tail)
        case "--brightness" :: value :: tail =>
          parseArgs(list.append(new Brightness(value.toInt)), tail)
        case "--flip" :: value ::tail =>
          parseArgs(list.appended(new Flip(value)), tail)
        case "--font-aspect-ratio" :: value :: tail =>
          parseArgs(list.appended(new FontAspectRatio(value)), tail)
        case unknown :: _ =>
          println("Unknown option " + unknown)
          println("Try run with --help to list the supported options")
          exit(1)
      }
    }

    parseArgs(ListBuffer[Command](), args.toList)
  }

}
