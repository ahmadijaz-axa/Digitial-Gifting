package com.ibm.jazzcashconsumer.view.digitalgifting.model

import androidx.annotation.DrawableRes

data class SpecialOccasionsItemModel(
    val title: String,
    val description: String,
    @DrawableRes val iconResId: Int,
    val daysLeft: Int,
    @DrawableRes val backgroundResId: Int? = null
)