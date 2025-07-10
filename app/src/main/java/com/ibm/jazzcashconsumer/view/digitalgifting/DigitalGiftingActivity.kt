package com.ibm.jazzcashconsumer.view.digitalgifting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ibm.jazzcashconsumer.R
import com.ibm.jazzcashconsumer.base.BaseActivity
import com.ibm.jazzcashconsumer.databinding.ActivityDigitalGiftingBinding
import com.ibm.jazzcashconsumer.view.digitalgifting.viewmodel.DigitalGiftingViewModel
import com.ibm.jazzcashconsumer.viewmodel.BaseViewModel

class DigitalGiftingActivity : BaseActivity(){

    private var _binding: ActivityDigitalGiftingBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: DigitalGiftingViewModel by viewModels()

    private lateinit var navController: NavController

    override fun getViewModel(): BaseViewModel? {
        return viewmodel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        _binding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_digital_gifting, null, false)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_digital_gifting) as NavHostFragment? ?: return
        navController = host.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_digital_gifting)

        navGraph.setStartDestination(R.id.digitalGiftingDashboardFragment)

        navController.graph = navGraph

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            binding.tvTitle.text = destination.label
        }

        binding.apply {
            ivBack.setOnClickListener {
                handlerBack()
            }

            ivHome.setOnClickListener {
                finish()
            }
        }
    }

    fun handlerBack() {
        navController.popBackStack()
    }

    companion object {
        fun startDigitalGiftingIntent(
            context: Context,
        ) {
            val intent = Intent(context, DigitalGiftingActivity::class.java)
            context.startActivity(intent)
        }
    }
}