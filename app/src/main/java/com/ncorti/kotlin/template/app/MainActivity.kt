package com.ncorti.kotlin.template.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ncorti.kotlin.template.app.databinding.ActivityMainBinding
import com.ncorti.kotlin.template.library.FactorialCalculator
import com.ncorti.kotlin.template.library.android.NotificationUtil
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {

    private val notificationUtil: NotificationUtil by lazy { NotificationUtil(this) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCompute.setOnClickListener {
            val input = binding.editTextFactorial.text.toString().toLong()
            @Suppress("SwallowedException")
            try {
                val result = FactorialCalculator.computeFactorial(input).toString()

                binding.textResult.text = result
                binding.textResult.visibility = View.VISIBLE
                binding.editTextFactorial.error = null

                notificationUtil.showNotification(
                    context = this,
                    title = getString(R.string.notification_title),
                    message = result
                )
            } catch (ex: IllegalStateException) {
                binding.editTextFactorial.error = getString(R.string.factorial_input_invalid)
            }
        }

        binding.buttonAppcompose.setOnClickListener {
            val intent = Intent(it.context, ComposeActivity::class.java)
            startActivity(intent)
        }
    }
}
