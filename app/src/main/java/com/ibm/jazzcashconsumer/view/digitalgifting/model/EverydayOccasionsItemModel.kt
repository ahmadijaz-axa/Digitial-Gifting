package com.ibm.jazzcashconsumer.view.digitalgifting.model

import androidx.annotation.DrawableRes

data class EverydayOccasionsItemModel(
    val title: String,
    @DrawableRes val iconResId: Int,
    val action: String,
)