package com.example.gridcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.gridcalculator.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private var canAddOperation = false
    private var canAddDecimal = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun numberAction(view : View) {
        if(view is MaterialButton) {
            if(view.text == ".") {
                if (canAddDecimal) {
                    binding.tvExpression.append(view.text)
                }
                canAddDecimal = false
            } else {
                binding.tvExpression.append(view.text)
            }
            canAddOperation = true
        }
    }

    fun operatorAction(view: View) {
        if(view is MaterialButton && canAddOperation) {
            binding.tvExpression.append(view.text)
            canAddOperation = false
            canAddDecimal = true
        }
    }

    fun allClearAction(v: View) {
        binding.tvAnswer.text = ""
        binding.tvExpression.text = ""
    }

    fun backspaceAction(v: View) {
        val length = binding.tvExpression.length()
        if(length > 0) {
            binding.tvExpression.text = binding.tvExpression.text.subSequence(0, length - 1)
        }
    }

    fun equalsAction(v: View) {
        binding.tvAnswer.text = Calculation(binding.tvExpression.text.toString()).answer
    }
}