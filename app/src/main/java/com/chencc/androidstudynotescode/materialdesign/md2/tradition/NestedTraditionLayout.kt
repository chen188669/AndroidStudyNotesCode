package com.chencc.androidstudynotescode.materialdesign.md2.tradition

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.widget.LinearLayout
import kotlin.math.abs

private const val TAG = "NestedTraditionLayout"
class NestedTraditionLayout : LinearLayout {

    private var mLastY = 0f

    private var mTouchSlop = 0

    var mTopViewHeight = 0
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
//        mTouchSlop = ViewConfiguration.get(context).scaledPagingTouchSlop
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        //外部拦截法
        val action = ev.action
        val y = ev.y
        when(action){
            MotionEvent.ACTION_DOWN ->{
                mLastY = y
                Log.i(TAG, "onInterceptTouchEvent:   mLastY : $mLastY ")
            }
            MotionEvent.ACTION_MOVE ->{
                val dy = mLastY - y
                Log.i(TAG, "onInterceptTouchEvent:   dy : $dy ")
                if (abs(dy) > mTouchSlop){
                    if (isHiddenTop(dy) || isShowTop(dy)){
                        //滚动头部
                        return true;
                    }
                }
                mLastY = y
            }
        }

        return super.onInterceptTouchEvent(ev)
    }

    /**
     * 视图向上滚动 隐藏头部
     * 滑动距离 dy大于0为向上滚动  且  顶部滚动距离大于头部view的高度
     */
    private fun isHiddenTop(dy : Float) : Boolean{
        Log.i(TAG, "isHiddenTop:  dy :$dy mTouchSlop : $mTouchSlop  scrollY : $scrollY   mTopViewHeight : $mTopViewHeight")
        return dy > 0 && scrollY < mTopViewHeight
    }

    /**
     * 视图向下滚动 显示头部
     *  滑动距离 dy小于0为向下滚动 且 顶部滚动距离大于0 并且 视图不可以向上滚动
     */
    private fun isShowTop(dy : Float) : Boolean{
        return dy < 0 && scrollY > 0 && !canScrollVertically(-1)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.action
        val y = event.y
        when(action){
            MotionEvent.ACTION_DOWN -> {
                mLastY = y
            }
            MotionEvent.ACTION_MOVE -> {
                val dy = mLastY - y
                if (abs(dy) > mTouchSlop){
                    scrollBy(0, dy.toInt())
                }
                mLastY = y
            }
            MotionEvent.ACTION_UP -> {}
        }

        return super.onTouchEvent(event)
    }


    override fun scrollTo(x: Int, y: Int) {
//        min(max(y, 0), mTopViewHeight)
        var sy = y
        if (sy < 0){
            sy = 0
        }
        if (sy > mTopViewHeight){
            sy = mTopViewHeight
        }

        super.scrollTo(x, sy)
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mTopViewHeight = getChildAt(0).measuredHeight
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val lp = getChildAt(2).layoutParams
        lp.height = measuredHeight - getChildAt(1).measuredHeight
        getChildAt(2).layoutParams = lp
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}