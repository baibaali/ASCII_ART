package transformation

object DefaultLinearTransformation extends LinearTransformation {
//  val symbols = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. "
//  val symbolsCount = 70
//  val symbols = " .:-=+*#%@"
  val symbols = "@%#*+=-:. "
  val symbolsCount = 10
  def getSymbol(value: Int): Char = {
    symbols.charAt(Math.min((value / (255 / symbolsCount.toDouble)).toInt, symbolsCount - 1))
  }
}
