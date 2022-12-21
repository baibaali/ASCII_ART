package image.`export`

import org.scalatest.FunSuite
import transformation.DefaultLinearTransformation

import java.io.{BufferedWriter, File, FileReader, FileWriter}
import scala.io.Source

class FileOutputTest extends FunSuite{

  test("File Output Test") {
    val text = "Text to write into file"
    val path = "src/test/resources/output/output.txt"
    val outputFile = new File(path)

    val fw = new FileWriter(outputFile)

    fw.write(text)
    fw.close()

    outputFile.delete()
  }

}
