package com.manuelcarvalho.ansicreator.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.manuelcarvalho.ansicreator.R


private const val TAG = "Canvas"

class AnsiCanvas(context: Context) : View(context) {

    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap

    private var touchX = 0.0f
    private var touchY = 0.0f

    private var canvasHeight = 0
    private var canvasWidth = 0

    private val backgroundColor =
        ResourcesCompat.getColor(resources, R.color.canvasBackground, null)
    private val drawColor0 = ResourcesCompat.getColor(resources, R.color.cga_white, null)
    private val drawColor1 = ResourcesCompat.getColor(resources, R.color.cga_yellow, null)
    private val drawColor2 = ResourcesCompat.getColor(resources, R.color.cga_light_cyan, null)
    private val drawColor3 = ResourcesCompat.getColor(resources, R.color.cga_light_red, null)
    private val drawColor4 = ResourcesCompat.getColor(resources, R.color.cga_light_green, null)

    // A 6x5 array of Int, all set to 0.
    var display = Array(80) { Array(25) { 0 } }

    private val paint0 = Paint().apply {
        color = drawColor0
        style = Paint.Style.STROKE
        strokeWidth = 5f
        textSize = 20f
    }
    private val paint1 = Paint().apply {
        color = drawColor1
        style = Paint.Style.STROKE
        strokeWidth = 5f
        textSize = 20f
    }
    private val paint2 = Paint().apply {
        color = drawColor2
        style = Paint.Style.STROKE
        strokeWidth = 5f
        textSize = 20f
    }
    private val paint3 = Paint().apply {
        color = drawColor3
        style = Paint.Style.STROKE
        strokeWidth = 5f
        textSize = 20f
    }
    private val paint4 = Paint().apply {
        color = drawColor4
        style = Paint.Style.STROKE
        strokeWidth = 5f
        textSize = 20f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        canvasWidth = w
        canvasHeight = h


        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundColor)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(extraBitmap, 0f, 0f, null)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        touchX = event.x
        touchY = event.y

//        for (x in -canvasWidth..canvasWidth) {
////            Log.d(TAG, " $x")
////            val x1 = x * touchX / 10
////            extraCanvas.drawPoint((x1 + 400), (x * x).toFloat(), paint)
////        }


        dispScreen()

        invalidate()

        return true
    }

    fun dispScreen() {
        display[40][12] = 1
        display[70][20] = 1
        val stepX = canvasWidth / 80
        val stepY = canvasHeight / 25

        for (x in 1..79) {
            for (y in 1..24) {
                if (display[x][y] == 1) {
                    val x1 = x * stepX
                    val y1 = y * stepY
                    //extraCanvas.drawPoint(x1.toFloat(), y1.toFloat(), paint)

                    for (x2 in x1..stepX + x1) {
                        for (y2 in y1..stepY + y1) {
                            extraCanvas.drawPoint(x2.toFloat(), y2.toFloat(), paint0)
                        }
                    }


                }
                if (display[x][y] == 2) {
                    val x1 = x * stepX
                    val y1 = y * stepY
                    //extraCanvas.drawPoint(x1.toFloat(), y1.toFloat(), paint)

                    for (x2 in x1..stepX + x1) {
                        for (y2 in y1..stepY + y1) {
                            extraCanvas.drawPoint(x2.toFloat(), y2.toFloat(), paint1)
                        }
                    }


                }
//                if (display[x][y] == 3) {
//                    val x1 = x * stepX
//                    val y1 = y * stepY
//                    //extraCanvas.drawPoint(x1.toFloat(), y1.toFloat(), paint)
//
//                    for (x2 in x1..stepX + x1) {
//                        for (y2 in y1..stepY + y1) {
//                            extraCanvas.drawPoint(x2.toFloat(), y2.toFloat(), paint2)
//                        }
//                    }
//
//
//                }
                if (display[x][y] == 3) {
                    val x1 = x * stepX
                    val y1 = y * stepY
                    //extraCanvas.drawPoint(x1.toFloat(), y1.toFloat(), paint)

                    for (x2 in x1..stepX + x1) {
                        for (y2 in y1..stepY + y1) {
                            extraCanvas.drawPoint(x2.toFloat(), y2.toFloat(), paint2)
                        }
                    }


                }
                if (display[x][y] == 4) {
                    val x1 = x * stepX
                    val y1 = y * stepY
                    //extraCanvas.drawPoint(x1.toFloat(), y1.toFloat(), paint)

                    for (x2 in x1..stepX + x1) {
                        for (y2 in y1..stepY + y1) {
                            extraCanvas.drawPoint(x2.toFloat(), y2.toFloat(), paint3)
                        }
                    }


                }
            }

        }
    }

    fun dispScreen1() {
        val stepX = canvasWidth / 80
        val stepY = canvasHeight / 25
        for (y in 0..canvasHeight step stepY)
            for (x in 0..canvasWidth step stepX) {
                for (x1 in x..stepX + x) {
                    for (y1 in y..stepY + y) {
                        extraCanvas.drawPoint(x1.toFloat(), y1.toFloat(), paint0)
                    }
                }
                drawChar(x, 50)
                //extraCanvas.drawPoint(x.toFloat(), (50).toFloat(), paint)
            }
    }

    fun drawChar(x: Int, y: Int) {

    }

    fun updateScreen(array: Array<Array<Int>>) {
        display = array
        invalidate()
    }
}