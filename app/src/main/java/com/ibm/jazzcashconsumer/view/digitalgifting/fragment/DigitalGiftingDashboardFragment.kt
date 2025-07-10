package com.ibm.jazzcashconsumer.view.digitalgifting.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.digitalgifting.BaseFragment
import com.ibm.jazzcashconsumer.viewmodel.BaseViewModel
import com.ibm.jazzcashconsumer.R
import com.ibm.jazzcashconsumer.databinding.FragmentDigitalGiftingDashboardBinding
import com.ibm.jazzcashconsumer.view.digitalgifting.adapter.EverydayOccasionsAdapter
import com.ibm.jazzcashconsumer.view.digitalgifting.adapter.SpecialOccasionsBannerAdapter
import com.ibm.jazzcashconsumer.view.digitalgifting.viewmodel.DigitalGiftingDashboardViewModel

class DigitalGiftingDashboardFragment : BaseFragment() {

    private var _binding: FragmentDigitalGiftingDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DigitalGiftingDashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_digital_gifting_dashboard,
                null,
                false,
            )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val specialOccasionAdapter = SpecialOccasionsBannerAdapter(emptyList())
        val everydayOccasionAdapter = EverydayOccasionsAdapter(emptyList()) { item, position ->
            findNavController().navigate(
                R.id.action_digitalGiftingDashboardFragment_to_digitalGiftOptionFragment,
                Bundle().apply { putString("flowName", item.action) })
        }
        binding.rvSpecialOccasionBanners.adapter = specialOccasionAdapter
        binding.rvSpecialOccasionBanners.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvEverydayOccasions.adapter = everydayOccasionAdapter
        binding.rvEverydayOccasions.layoutManager = GridLayoutManager(requireContext(), 3)

        viewModel.specialOccasions.observe(viewLifecycleOwner) { specialOccasions ->
            specialOccasionAdapter.updateData(specialOccasions)
            if (specialOccasions.size > 1) {
                binding.dotsIndicatorCards.visibility = View.VISIBLE
                binding.dotsIndicatorCards.initDots(specialOccasions.size)
            } else {
                binding.dotsIndicatorCards.visibility = View.GONE
            }
        }
        viewModel.everydayOccasions.observe(viewLifecycleOwner) { everydayOccasions ->
            everydayOccasionAdapter.updateData(everydayOccasions)
        }

        binding.rvSpecialOccasionBanners.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val position = layoutManager.findFirstCompletelyVisibleItemPosition()
                    if (position != RecyclerView.NO_POSITION) {
                        binding.dotsIndicatorCards.setDotSelection(position)
                    }
                }
            }
        })

        viewModel.loadOccasions()
    }

    override fun getViewModel(): BaseViewModel? {
        return viewModel
    }
}