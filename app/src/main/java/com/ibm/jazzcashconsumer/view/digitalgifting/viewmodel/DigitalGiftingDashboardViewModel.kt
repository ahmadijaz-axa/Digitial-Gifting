package com.ibm.jazzcashconsumer.view.digitalgifting.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ibm.jazzcashconsumer.viewmodel.BaseViewModel
import com.ibm.jazzcashconsumer.R
import com.ibm.jazzcashconsumer.view.digitalgifting.model.EverydayOccasionsItemModel
import com.ibm.jazzcashconsumer.view.digitalgifting.model.SpecialOccasionsItemModel

class DigitalGiftingDashboardViewModel() : BaseViewModel() {
    val specialOccasions = MutableLiveData<List<SpecialOccasionsItemModel>>()
    val everydayOccasions = MutableLiveData<List<EverydayOccasionsItemModel>>()

    fun loadOccasions() {
        // Sample data matching the provided image
        val specialOccasionsSample = listOf(
            SpecialOccasionsItemModel(
                title = "SpEidi",
                description = "Wishing you very Happy Eid!",
                iconResId = R.drawable.frame, // Replace with actual Eid icon resource
                daysLeft = 8,
                backgroundResId = R.drawable.ic_gradient_special_occasions_card // Replace with actual background if needed
            ),
            SpecialOccasionsItemModel(
                title = "SpEidi",
                description = "Wishing you very Happy Eid!",
                iconResId = R.drawable.frame, // Replace with actual Eid icon resource
                daysLeft = 8,
                backgroundResId = R.drawable.ic_gradient_special_occasions_card // Replace with actual background if needed
            )
        )

        val everydayOccasionsSample = listOf(
            EverydayOccasionsItemModel("Birthday Blessing", R.drawable.frame,"1"),
            EverydayOccasionsItemModel("Hello Baby", R.drawable.frame,"2"),
            EverydayOccasionsItemModel("Thank You", R.drawable.frame,"3"),
            EverydayOccasionsItemModel("Anniversary", R.drawable.frame,"4"),
            EverydayOccasionsItemModel("Promotions", R.drawable.frame,"5"),
            EverydayOccasionsItemModel("House Warming", R.drawable.frame,"6"),
            EverydayOccasionsItemModel("Graduation", R.drawable.frame,"7"),
            EverydayOccasionsItemModel("Salammi", R.drawable.frame,"8"),
            EverydayOccasionsItemModel("Get Well Soon", R.drawable.frame,"9"),
            EverydayOccasionsItemModel("Cheers To You", R.drawable.frame,"10"),
        )

        specialOccasions.value = specialOccasionsSample
        everydayOccasions.value = everydayOccasionsSample
    }
} 