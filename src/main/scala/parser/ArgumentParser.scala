package parser

import command.Command
import command.`export`.{ConsoleOutput, FileOutput}
import command.filter.{Brightness, Flip, FontAspectRatio, Invert, LoadImage, Rotate, Scale}

import scala.collection.mutable.{ListBuffer}
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

  def parse(args: Array[String]) = {

    if (args.length == 0)
      println(usage)

    //TODO: convert to int/double properly
    def parseArgs(list: ListBuffer[Command], argumentList: List[String]): ListBuffer[Command] = {
      argumentList match {
        case Nil => list
        case "--help" :: value =>
          println(usage)
          exit(0)
        case "--image" :: value :: tail =>
          parseArgs(list.append(new LoadImage(value)), tail)
        case "--output-file" :: value :: tail =>
          parseArgs(list.append(new FileOutput(value)), tail)
        case "--output-console" :: tail =>
          parseArgs(list.append(new ConsoleOutput()), tail)
        case "--rotate" :: value :: tail =>
          parseArgs(list.append(new Rotate(value.toInt)), tail)
        case "--scale" :: value :: tail =>
          parseArgs(list.append(new Scale(value.toDouble)), tail)
        case "--invert" :: tail =>
          parseArgs(list.append(new Invert()), tail)
        case "--brightness" :: value :: tail =>
          parseArgs(list.append(new Brightness(value.toInt)), tail)
        case "--flip" :: value ::tail =>
          parseArgs(list.append(new Flip(value)), tail)
        case "--font-aspect-ratio" :: value :: tail =>
          parseArgs(list.append(new FontAspectRatio(value)), tail)
        case unknown :: _ =>
          println("Unknown option " + unknown)
          println("Try run with --help to list the supported options")
          exit(1)
      }
    }

    val options = parseArgs(ListBuffer[Command](), args.toList)
    println(options)
  }

}
