package com.ibm.jazzcashconsumer.base

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ibm.jazzcashconsumer.viewmodel.BaseViewModel


/**
 * @author Abdul Rahman
 */
@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity(){
    abstract fun getViewModel(): BaseViewModel?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun restart() {
        startActivity(intent)
        finish()
    }
}