package image.loader

import image.loader.fileLoader.JPGImageLoader
import image.pixel.{RGBAPixel, RGBPixel}
import org.scalatest.FunSuite

class JPGImageLoaderTest extends FunSuite{

  test("JPG Image Loader testing") {
    val image = new JPGImageLoader("src/test/resources/input/test_rgb.jpg").load()

    assert(image.getHeight == 2)
    assert(image.getWidth == 2)

    assert(image.getPixel(0, 0).isInstanceOf[RGBAPixel])
    assert(image.getPixel(0, 1).isInstanceOf[RGBAPixel])
    assert(image.getPixel(1, 0).isInstanceOf[RGBAPixel])
    assert(image.getPixel(1, 1).isInstanceOf[RGBAPixel])

  }
}
