package com.ibm.jazzcashconsumer.view.digitalgifting.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.digitalgifting.BaseFragment
import com.ibm.jazzcashconsumer.viewmodel.BaseViewModel
import com.ibm.jazzcashconsumer.databinding.FragmentDigitalGiftOptionBinding

class DigitalGiftOptionFragment : BaseFragment() {

    private lateinit var binding: FragmentDigitalGiftOptionBinding

    override fun getViewModel(): BaseViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!::binding.isInitialized) {
            binding = FragmentDigitalGiftOptionBinding.inflate(inflater, container, false)
        }
        return binding.root
    }
}