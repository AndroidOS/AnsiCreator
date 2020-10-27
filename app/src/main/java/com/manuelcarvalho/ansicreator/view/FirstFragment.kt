package com.manuelcarvalho.ansicreator.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.manuelcarvalho.ansicreator.R
import com.manuelcarvalho.ansicreator.viewmodel.AppViewModel
import kotlinx.android.synthetic.main.fragment_first.*


private const val TAG = "FirstFragment"
class FirstFragment : Fragment() {

    private lateinit var view2: AnsiCanvas
    private lateinit var viewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[AppViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        var view1 = activity?.applicationContext?.let { AnsiCanvas(it) }

        canLay.addView(view1)
        if (view1 != null) {
            view2 = view1
        }
        observeViewModel()

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.d(TAG, "$progress")
                viewModel.setSeekBarValue(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
    }

    private fun observeViewModel() {
        Log.d(TAG, "ObserveViewModel started")
        viewModel.imageArray.observe(viewLifecycleOwner, Observer { image ->
            image?.let {
                view2.updateScreen(it)
                Log.d(TAG, "observeViewModel fired")
                displayArray(it)
            }
        })

    }

    private fun displayArray(array: kotlin.Array<kotlin.Array<Int>>) {
        for (n in array) {
            for (x in n) {

                val a: Int = x
                Log.d(TAG, "${a}")
            }
        }
    }
}