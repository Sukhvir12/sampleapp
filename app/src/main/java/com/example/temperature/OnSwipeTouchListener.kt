package com.example.temperature

import android.util.Log
import android.view.MotionEvent
import android.view.View

class OnSwipeTouchListener(private val v: View, private val listener: OnSwipeEvent, private val position: Int) : View.OnTouchListener {
    private val min_distance = 100
    private var downX = 0f
    private var downY: Float = 0f
    private var upX: Float = 0f
    private var upY: Float = 0f
    private var view: View
    private var onSwipeEvent : OnSwipeEvent
    private var mPosition : Int = 0

    init {
        view = v
        onSwipeEvent = listener
        view.setOnTouchListener(this)
        mPosition = position
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//        Log.d("swipe","onTouch ==${event?.action}")
        when (event?.action) {
            MotionEvent.ACTION_UP -> {
                downX = event.x
                downY = event.y
            }
            MotionEvent.ACTION_DOWN -> {
                upX = event.getX()
                upY = event.getY()

                val deltaX = downX - upX
                val deltaY = downY - upY

                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (Math.abs(deltaX) > min_distance) {
                        // left or right
                        if (deltaX < 0) {
                            this.onLeftToRightSwipe();
                            return true;
                        }
                        if (deltaX > 0) {
                            this.onRightToLeftSwipe();
                            return true;
                        }
                    } else {
                        //not long enough swipe...
                        return false;
                    }
                }
            }
        }
        return false
    }

    fun onLeftToRightSwipe() {
        onSwipeEvent.SwipeEventDetected(view, SwipeTypeEnum.LEFT_TO_RIGHT, mPosition)
    }

    fun onRightToLeftSwipe() {
        onSwipeEvent.SwipeEventDetected(view, SwipeTypeEnum.RIGHT_TO_LEFT, mPosition)
    }

    interface OnSwipeEvent {
        fun SwipeEventDetected(v: View, SwipeType: SwipeTypeEnum, position: Int)
    }

    enum class SwipeTypeEnum {
        RIGHT_TO_LEFT, LEFT_TO_RIGHT, TOP_TO_BOTTOM, BOTTOM_TO_TOP
    }
}