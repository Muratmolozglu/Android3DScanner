package com.muratmolozglu.android3dscanner

import androidx.camera.core.ImageProxy

object ScanManager {
    fun processFrame(imageProxy: ImageProxy) {
        // TODO: Replace with ARCore depth extraction or point cloud generation.
        // This placeholder keeps the pipeline ready for future scanner logic.
        imageProxy.close()
    }
}
