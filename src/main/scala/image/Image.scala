package image

import image.pixel.{Pixel, PixelCollection}

abstract class Image[P <: Pixel] extends PixelCollection[P] {
}
