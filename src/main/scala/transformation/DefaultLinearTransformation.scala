package transformation

import scala.sys.exit

object DefaultLinearTransformation extends LinearTransformation {
  var symbols = "@%#*+=-:. "
  var symbolsCount: Int = symbols.length

  def getSymbol(value: Int): Char = {
    symbols.charAt(Math.min((value / (255 / symbolsCount.toDouble)).toInt, symbolsCount - 1))
  }

  def setSymbols(table: String): Unit = {
    if (table.length <= 0 || table.length > 255) {
      println("Invalid symbols table")
      exit(1)
    }
    symbols = table
    symbolsCount = symbols.length
  }
}
