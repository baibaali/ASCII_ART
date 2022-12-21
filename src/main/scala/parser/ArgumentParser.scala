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

    if (args.length == 0)
      println(usage)

    var loader: ImageLoader = new RandomImageGenerator()
    var output = new ListBuffer[ImageWriter]

    //TODO: convert to int/double properly
    def parseArgs(list: ListBuffer[Command], argumentList: List[String]): (ListBuffer[Command], ImageLoader, ListBuffer[ImageWriter]) = {
      argumentList match {
        case Nil => (list, loader, output)
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
          output.append(new FileOutput(value))
          parseArgs(list, tail)
        case "--output-console" :: tail =>
          output.append(new ConsoleOutput())
          parseArgs(list, tail)
        case "--rotate" :: value :: tail =>
          parseArgs(list.append(new Rotate(value.toInt)), tail)
        case "--scale" :: value :: tail =>
          parseArgs(list.append(new Scale(value.toDouble)), tail)
        case "--invert" :: tail =>
          parseArgs(list.append(new Invert()), tail)
        case "--brightness" :: value :: tail =>
          parseArgs(list.append(new Brightness(value.toInt)), tail)
        case "--flip" :: value :: tail =>
          parseArgs(list.append(new Flip(value)), tail)
        case "--custom-table" :: value :: tail =>
          DefaultLinearTransformation.setSymbols(value)
          parseArgs(list, tail)
        case unknown :: _ =>
          println("Unknown option " + unknown)
          println("Try run with --help to list the supported options")
          exit(1)
      }
    }

    parseArgs(ListBuffer[Command](), args.toList)
  }

}
