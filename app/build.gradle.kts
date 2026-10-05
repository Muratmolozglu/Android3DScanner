package com.muratmolozglu.android3dscanner

import androidx.camera.core.ImageProxy
import java.io.File

data class ScanPoint(
    val x: Float,
    val y: Float,
    val z: Float
)

object ScanManager {
    private val points = mutableListOf<ScanPoint>()
    private val lock = Any()

    fun processFrame(imageProxy: ImageProxy) {
        try {
            val plane = imageProxy.planes.firstOrNull() ?: run {
                imageProxy.close()
                return
            }
            val buffer = plane.buffer
            val data = ByteArray(buffer.remaining())
            buffer.get(data)

            val width = imageProxy.width
            val height = imageProxy.height
            val step = 16

            synchronized(lock) {
                for (y in 0 until height step step) {
                    for (x in 0 until width step step) {
                        val index = y * width + x
                        if (index < data.size) {
                            val brightness = data[index].toInt() and 0xFF
                            if (brightness in 40..230) {
                                val normalizedX = (x.toFloat() / width.toFloat()) - 0.5f
                                val normalizedY = (y.toFloat() / height.toFloat()) - 0.5f
                                val depth = 1.2f + ((255 - brightness) / 255f) * 2.5f

                                points.add(
                                    ScanPoint(
                                        normalizedX * 4f,
                                        normalizedY * 4f,
                                        depth
                                    )
                                )
                            }
                        }
                    }
                }
            }
        } catch (_: Exception) {
            // Ignore per-frame processing failures and continue scanning.
        } finally {
            imageProxy.close()
        }
    }

    fun clear() {
        synchronized(lock) {
            points.clear()
        }
    }

    fun exportToObj(outputDir: File): File {
        val file = File(outputDir, "scan_model.obj")
        val builder = StringBuilder()
        builder.appendLine("# Android 3D Scanner model")
        builder.appendLine("o scanned_object")

        synchronized(lock) {
            for (point in points) {
                builder.appendLine("v ${point.x} ${point.y} ${point.z}")
            }
        }

        file.writeText(builder.toString())
        return file
    }

    fun pointCount(): Int {
        synchronized(lock) {
            return points.size
        }
    }
}
