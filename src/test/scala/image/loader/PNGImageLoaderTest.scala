package image.loader

import image.loader.fileLoader.PNGImageLoader
import image.pixel.RGBAPixel
import org.scalatest.FunSuite

class PNGImageLoaderTest extends FunSuite {
  test("PNG Image Loader testing") {
    val image = new PNGImageLoader("src/test/resources/input/test_rgb.png").load()

    assert(image.getHeight == 2)
    assert(image.getWidth == 2)

    assert(image.getPixel(0, 0).isInstanceOf[RGBAPixel])
    assert(image.getPixel(0, 1).isInstanceOf[RGBAPixel])
    assert(image.getPixel(1, 0).isInstanceOf[RGBAPixel])
    assert(image.getPixel(1, 1).isInstanceOf[RGBAPixel])
  }
}
