package com.app.digitalgifting

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ibm.jazzcashconsumer.viewmodel.BaseViewModel

/**
 * @author Abdul Rahman
 */
abstract class BaseFragment : BottomSheetDialogFragment() {
    abstract fun getViewModel(): BaseViewModel?


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            val imm: InputMethodManager =
                it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    open fun didAppear() {}
}




