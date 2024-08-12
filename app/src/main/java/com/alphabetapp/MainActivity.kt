package com.alphabetapp

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alphabetapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private val alphabetArray = ('A'..'Z').toList()
  /*  private val colorsArray = arrayOf(
        Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN, Color.MAGENTA,
        Color.DKGRAY, Color.GRAY, Color.BLACK, Color.WHITE, Color.rgb(255, 165, 0), // Orange
        Color.rgb(75, 0, 130), // Indigo
        Color.rgb(238, 130, 238) // Violet
    )
*/
  private val colorsArray = arrayOf(
        Color.parseColor("#FF5722"), // Deep Orange
        Color.parseColor("#3F51B5"), // Indigo
        Color.parseColor("#4CAF50"), // Green
        Color.parseColor("#AC0611"), // Yellow
        Color.parseColor("#FF9800"), // Orange
        Color.parseColor("#2196F3"), // Blue
        Color.parseColor("#9C27B0"), // Purple
        Color.parseColor("#00BCD4")  // Cyan
    )

    private lateinit var binding: ActivityMainBinding

    private val soundMap = mapOf(
        'A' to R.raw.a,
        'B' to R.raw.b,
        'C' to R.raw.c,
        'D' to R.raw.d,
        'E' to R.raw.e,
        'F' to R.raw.f,
        'G' to R.raw.g,
        'H' to R.raw.h,
        'I' to R.raw.i,
        'J' to R.raw.j,
        'K' to R.raw.k,
        'L' to R.raw.l,
        'M' to R.raw.m,
        'N' to R.raw.n,
        'O' to R.raw.o,
        'P' to R.raw.p,
        'Q' to R.raw.q,
        'R' to R.raw.r,
        'S' to R.raw.s,
        'T' to R.raw.t,
        'U' to R.raw.u,
        'V' to R.raw.v,
        'W' to R.raw.w,
        'X' to R.raw.x,
        'Y' to R.raw.y,
        'Z' to R.raw.z
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        for ((index, alphabet) in alphabetArray.withIndex()) {
            val button = Button(this).apply {
                text = alphabet.toString()
                textSize = 30f
                setTextColor(Color.WHITE)
                setBackgroundColor(colorsArray[index % colorsArray.size])
                setOnClickListener {
                    onAlphabetClick(alphabet)
                }
            }
            val params = GridLayout.LayoutParams().apply {
                width = 0
                height = GridLayout.LayoutParams.WRAP_CONTENT
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                setMargins(2, 2, 2, 2)
            }
            binding.alphabetGrid.addView(button, params)
        }
    }

    private fun onAlphabetClick(alphabet: Char) {
        binding.alphabetWord.text = alphabet.toString()
        playSound(alphabet)
        scaleAnimation(binding.alphabetWord)
    }

    private fun scaleAnimation(alphabetWord: TextView) {
        val scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.5f, 1f)
        val scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.5f, 1f)

        val scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(alphabetWord, scaleX, scaleY)
        scaleAnimator.duration = 1000
        scaleAnimator.repeatCount = ObjectAnimator.RESTART
        scaleAnimator.start()
    }

    private fun playSound(alphabet: Char) {
        mediaPlayer?.release()
        val soundResource = soundMap[alphabet] ?: return
        mediaPlayer = MediaPlayer.create(this, soundResource)
        mediaPlayer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}