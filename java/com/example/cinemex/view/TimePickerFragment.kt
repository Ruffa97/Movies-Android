package com.example.cinemex.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.NumberPicker
import android.widget.TextView
import com.example.cinemex.R
import com.example.cinemex.view.common.fragments.BaseFragment

class TimePickerFragment(): BaseFragment() {
    private var time: Int = 180
    private lateinit var numberPickerView: View
    private lateinit var numberPicker: NumberPicker
    private lateinit var closeButton: ImageButton
    private lateinit var applyButton: TextView
    private lateinit var onTimeChangeListener: onTimeChange

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null){
            time = savedInstanceState.getInt("LAST_TIME")
        }
    }

    fun newInstance(): TimePickerFragment{
        return TimePickerFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            onTimeChangeListener = context as onTimeChange
        } catch (e: ClassCastException) {
            throw ClassCastException(
                activity.toString() + " must implement onTimeChange"
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        numberPickerView = inflater.inflate(R.layout.configuration_now,container,false)
        numberPicker = numberPickerView.findViewById(R.id.numberPicker)
        closeButton = numberPickerView.findViewById(R.id.close)
        applyButton = numberPickerView.findViewById(R.id.apply)
        numberPicker.displayedValues = resources.getStringArray(R.array.time_settings)
        numberPicker.minValue = 1
        numberPicker.maxValue = 6
        numberPicker.value = time
        numberPicker.wrapSelectorWheel = true
        closeButton.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        applyButton.setOnClickListener {
            onTimeChangeListener.onFetchMoviesNewTime(numberPicker.displayedValues[numberPicker.value - 1].toInt())
            time = numberPicker.value
            fragmentManager?.popBackStack()
        }
        return numberPickerView
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("LAST_TIME",time)
    }

    interface onTimeChange{
        fun onFetchMoviesNewTime(time: Int)
    }
}