package com.ibm.jazzcashconsumer.util

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import com.app.digitalgifting.ViewUtil
import com.ibm.jazzcashconsumer.R

class DotsIndicatorRound: LinearLayout {

    var selection: Int = 0
        private set
    private var dotsCount: Int = -1
    private var dotHeight: Int = ViewUtil.dpToPx(6)
    private var dotWidth: Int = ViewUtil.dpToPx(6)
    private var dotSelectedDotWidth = ViewUtil.dpToPx(8)
    var marginsBetweenDots: Int = ViewUtil.dpToPx(8)

    //var selectedDotScaleFactor: Float = 1.4f
    var selectedDotResource: Int = R.drawable.circle_selected_dot_indicator
    var unselectedDotResource: Int = R.drawable.circle_grey

    var onSelectListener: ((position: Int) -> Unit)? = null

    constructor(context: Context) : super(context) {
        orientation = HORIZONTAL
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        gravity = Gravity.CENTER
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        orientation = HORIZONTAL
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        gravity = Gravity.CENTER

        val ta = getContext().obtainStyledAttributes(
            attrs,
            R.styleable.DotsIndicator, 0, 0
        )
        dotsCount = ta.getInt(R.styleable.DotsIndicator_dots_count, 3)

        selectedDotResource =
            ta.getResourceId(R.styleable.DotsIndicator_selected_dot_resource, selectedDotResource)

        unselectedDotResource = ta.getResourceId(
            R.styleable.DotsIndicator_unselected_dot_resource,
            unselectedDotResource
        )


        dotHeight =
            ta.getDimensionPixelSize(R.styleable.DotsIndicator_dot_height, dotHeight)

        dotWidth =
            ta.getDimensionPixelSize(R.styleable.DotsIndicator_dot_width, dotWidth)

        dotSelectedDotWidth =
            ta.getDimensionPixelSize(
                R.styleable.DotsIndicator_dot_selected_width,
                dotSelectedDotWidth
            )

        marginsBetweenDots =
            ta.getDimensionPixelSize(
                R.styleable.DotsIndicator_margins_between_dots,
                marginsBetweenDots
            )

        dotHeight = context.resources.getDimensionPixelSize(R.dimen.figma_6_dp)
        dotWidth = context.resources.getDimensionPixelSize(R.dimen.figma_6_dp)
        dotSelectedDotWidth = context.resources.getDimensionPixelSize(R.dimen.figma_6_dp)

        initDots(dotsCount)
        ta.recycle()
    }

    fun initDots(dotsCount: Int) {
        this.dotsCount = dotsCount
        removeAllViews()
        for (i: Int in 0 until dotsCount) {
            val dot = ImageView(context)
            dot.id = i
            dot.tag = i

            dot.layoutParams = LayoutParams(dotHeight, dotWidth).apply {
                this.marginEnd = marginsBetweenDots
                this.gravity = Gravity.CENTER_VERTICAL
            }


            dot.scaleType = ImageView.ScaleType.FIT_XY

            if (selection == i) {
                dot.layoutParams = LayoutParams(dotSelectedDotWidth, dotHeight).apply {
                    this.marginEnd = marginsBetweenDots
                    this.gravity = Gravity.CENTER_VERTICAL
                }

                dot.setImageResource(selectedDotResource)
            } else {
                dot.setImageResource(unselectedDotResource)
            }


            dot.setOnClickListener {
                onSelectListener?.invoke(it.tag as Int)
                setDotSelection(it.tag as Int)
            }
            addView(dot)
        }
        setDotSelection(selection)
    }

    fun setDotSelection(position: Int?) {
        if (position == selection || selection > dotsCount)
            return
        if (position != null){
            val newSelection: ImageView? = findViewById(position)
            newSelection?.layoutParams =
                LayoutParams(dotSelectedDotWidth, dotHeight).apply {
                    this.marginEnd = marginsBetweenDots
                    this.gravity = Gravity.CENTER_VERTICAL
                }
            newSelection?.setImageResource(selectedDotResource)
            selection = if (newSelection?.tag != null) {
                newSelection?.tag as Int
            } else {
                0
            }
        }

//        val animationListener = object : Animator.AnimatorListener {
//            override fun onAnimationRepeat(animation: Animator?) {}
//
//            override fun onAnimationEnd(animation: Animator?) {
//
//               selectedDot.scaleX = 1f
//                selectedDot.scaleY = 1f
//            }

//            override fun onAnimationCancel(animation: Animator?) {}
//
//            override fun onAnimationStart(animation: Animator?) {}
//
//        }

        for (i: Int in 0 until selection) {
            val selectedDot: ImageView? = findViewWithTag(i)
            selectedDot?.layoutParams =
                LayoutParams(dotWidth, dotHeight).apply {
                    this.marginEnd = marginsBetweenDots
                    this.gravity = Gravity.CENTER_VERTICAL
                }
            selectedDot?.setImageResource(selectedDotResource)
        }
        for (i: Int in selection + 1 until dotsCount) {
            val selectedDot: ImageView? = findViewWithTag(i)
            selectedDot?.layoutParams =
                LayoutParams(dotWidth, dotHeight).apply {
                    this.marginEnd = marginsBetweenDots
                    this.gravity = Gravity.CENTER_VERTICAL
                }
            selectedDot?.setImageResource(unselectedDotResource)
        }
    }

    fun setDotCompleted() {
        selection = -1
        for (i: Int in 0 until dotsCount) {
            val selectedDot: ImageView? = findViewWithTag(i)
            selectedDot?.layoutParams =
                LayoutParams(dotWidth, dotHeight).apply {
                    this.marginEnd = marginsBetweenDots
                    this.gravity = Gravity.CENTER_VERTICAL
                }
            selectedDot?.setImageResource(selectedDotResource)
        }
    }

    fun setSelectedDotColor(colorResId:Int){
        selectedDotResource = colorResId
        this.requestLayout()
        this.invalidate()
    }

    fun changePreviousDotColor(currentDot:Int) {
        for (i: Int in 0 until currentDot) {
            val selectedDot: ImageView = findViewWithTag(i)
            selectedDot.layoutParams =
                LayoutParams(dotWidth, dotHeight).apply {
                    this.marginEnd = marginsBetweenDots
                    this.gravity = Gravity.CENTER_VERTICAL
                }
            selectedDot.setImageResource(unselectedDotResource)
        }
    }


}