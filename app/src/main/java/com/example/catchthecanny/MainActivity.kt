package com.example.catchthecanny

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.inputmethod.InputBinding
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCaller
import androidx.appcompat.app.AlertDialog
import com.example.catchthecanny.databinding.ActivityMainBinding
import com.google.android.material.color.utilities.Score
import java.util.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var score = 0
    var imageArray = ArrayList<ImageView>()
    var runnable = Runnable {}
    var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView.setOnClickListener { increaseScore() }
        binding.imageView2.setOnClickListener { increaseScore() }
        binding.imageView3.setOnClickListener { increaseScore() }
        binding.imageView4.setOnClickListener { increaseScore() }
        binding.imageView5.setOnClickListener { increaseScore() }
        binding.imageView6.setOnClickListener { increaseScore() }
        binding.imageView7.setOnClickListener { increaseScore() }
        binding.imageView8.setOnClickListener { increaseScore() }
        binding.imageView9.setOnClickListener { increaseScore() }
        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)

        hideImages()


        object : CountDownTimer(15500,1000) {
            override fun onTick(p0: Long) {
               binding.timeText.text="Time: ${p0/1000}"
            }

            override fun onFinish() {
                binding.timeText.text = "Time: 0"
                handler.removeCallbacks(runnable)
                for (i in imageArray) {
                    i.visibility = INVISIBLE
                }

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over!")
                alert.setMessage("Do u wanna play again?")
                alert.setPositiveButton("Yes", DialogInterface.OnClickListener{dialogInterface, i ->
                    val intentFromMain = intent
                    finish()
                    startActivity(intentFromMain)
                })
                alert.setNegativeButton("No!", DialogInterface.OnClickListener{dialogInterface, i ->
                    Toast.makeText(this@MainActivity, "Game Over!", Toast.LENGTH_LONG).show()
                    })
                alert.show()
            }
        }.start()

    }

    fun hideImages() {
        runnable = object : Runnable {
            override fun run() {
                for (i in imageArray)
                    i.visibility = INVISIBLE
                val rnd = Random()
                val randomIndex = rnd.nextInt(9)
                imageArray[randomIndex].visibility = VISIBLE

                handler.postDelayed(runnable, 1000)
            }
        }
        handler.post(runnable)
    }
    fun increaseScore() {
        score++
        binding.scoreText.text = score.toString()
    }
}




