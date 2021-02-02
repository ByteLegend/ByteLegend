package common.utils

import org.khronos.webgl.Uint8Array

// https://www.npmjs.com/package/get-image-pixels
@JsModule("get-image-pixels")
@JsNonModule
external fun getPixels(image: Any): Uint8Array
