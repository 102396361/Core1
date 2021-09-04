package com.example.core1

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.graphics.toColorInt

class MainActivity : AppCompatActivity() {
    var saveResult: Int = 0
    var mediaPlayer: MediaPlayer? = null

    override fun onStart() {
        super.onStart()
        Log.i("LIFECYCLE", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("LIFECYCLE", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("LIFECYCLE", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("LIFECYCLE", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LIFECYCLE", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("LIFECYCLE", "onRestart")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("LIFECYCLE", "onCreate")

        var score = findViewById<TextView>(R.id.score)
        score.text = saveResult.toString()
        val scoreBtn = findViewById<Button>(R.id.scoreBtn)
        val stealBtn = findViewById<Button>(R.id.stealBtn)
        val resetBtn = findViewById<Button>(R.id.resetBtn)

        savedInstanceState?.let {
            saveResult = it.getInt("Answer")
            score.text = saveResult.toString()
        }

        scoreBtn.setOnClickListener {
            if(saveResult in 0..14){
                saveResult++

                if(saveResult in 5..9){
                    score.setTextColor(Color.parseColor("#FF1181DA"))
                } else if (saveResult in 10..15){
                    score.setTextColor(Color.parseColor("#FF4CAF50"))
                } else{
                    score.setTextColor(Color.parseColor("#FF000000"))
                }

                if(saveResult == 15){
                    mediaPlayer = MediaPlayer.create(this, R.raw.victory)
                    mediaPlayer!!.start()
                }

                score.text = saveResult.toString()
            }
        }

        stealBtn.setOnClickListener {
            if(saveResult in 1..15){
                saveResult--
                if(saveResult in 5..9){
                    score.setTextColor(Color.parseColor("#FF1181DA"))
                } else if (saveResult in 10..15){
                    score.setTextColor(Color.parseColor("#FF4CAF50"))
                } else {
                    score.setTextColor(Color.parseColor("#FF000000"))
                }
                score.text = saveResult.toString()
            }
        }

        resetBtn.setOnClickListener {
            saveResult = 0
            score.setTextColor(Color.parseColor("#FF000000"))
            score.text = saveResult.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("ANSWER", saveResult)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        saveResult = savedInstanceState.getInt("ANSWER")
        val score = findViewById<TextView>(R.id.score)
        if(saveResult in 5..9){
            score.setTextColor(Color.parseColor("#FF1181DA"))
        } else if (saveResult in 10..15){
            score.setTextColor(Color.parseColor("#FF4CAF50"))
        } else {
            score.setTextColor(Color.parseColor("#FF000000"))
        }
        score.text = saveResult.toString()
        Log.i("LIFECYCLE", "restoreInstanceState $saveResult")
    }
}