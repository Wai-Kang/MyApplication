package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var diceImg: ImageView
    lateinit var numberText: TextView
    lateinit var editPlayerName: EditText
    lateinit var playerNameTV: TextView
    lateinit var binding: ActivityMainBinding
    private val gameInfo: MyName = MyName("CHONG")
    private var totalScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        diceImg = findViewById(R.id.diceImg)
        numberText = findViewById(R.id.numberText)
        //val rollButton: Button = findViewById(R.id.rollButton)
        val rollButton = findViewById<Button>(R.id.rollButton)
        editPlayerName = findViewById(R.id.editPlayerName)
        playerNameTV = findViewById(R.id.playerName)
        binding.gameInfo = gameInfo
        binding.rollButton.setOnClickListener { rollDice() }
//        rollButton.setOnClickListener{rollDice()}
//        val updateButton = findViewById<Button>(R.id.updatePlayerNameBtn)
        binding.updatePlayerNameBtn.setOnClickListener{updatePlayerName(it)}
    }

    private fun updatePlayerName(view: View){
//        playerNameTV.text = editPlayerName.text
        binding.apply{
            gameInfo?.playerName = editPlayerName.text.toString()
            invalidateAll()
        }
//        binding.gameInfo.playerName = gameInfo.playerName(binding.EditPlayerName.text)
//        binding.editPlayerName.text = binding.editPlayerName.text
        //hide keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        editPlayerName.text.clear()
    }

    private fun rollDice(){
        val randomNumb = (1..6).random()
        binding.numberText.text = randomNumb.toString()
        totalScore+=randomNumb
        binding.numberText.text = totalScore.toString()
        //val numberText: TextView = findViewById(R.id.numberText)
        //val diceImg: ImageView = findViewById(R.id.diceImg)
//        numberText.text = randomNumb.toString()
        val imgSrc = when (randomNumb){
            1 ->R.drawable.dice_1
            2 ->R.drawable.dice_2
            3 ->R.drawable.dice_3
            4 ->R.drawable.dice_4
            5 ->R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        binding.diceImg.setImageResource(imgSrc)
        Toast.makeText(this,randomNumb.toString(),
        Toast.LENGTH_SHORT).show()
    }
}