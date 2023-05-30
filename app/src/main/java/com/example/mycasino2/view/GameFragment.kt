package com.example.mycasino2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.mycasino2.*
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameFragment : Fragment() {

    var time:Long = 0
    var listCards = emptyList<String>()
    var hiddenCard = ""
    val url = "http://37.27.9.28/9/"
    var correct = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when(requireArguments().getString(COMPLEXITY)){
            EASY -> { time = 8000 }
            MEDIUM -> { time = 6000 }
            HARD -> { time = 4000 }
        }

        getRandomCards()
        loadRandomCards()

        id_game_image1.setOnClickListener {
            checkAnswer(listCards[0],id_game_image1)
        }

        id_game_image2.setOnClickListener {
            checkAnswer(listCards[1],id_game_image2)
        }

        id_game_image3.setOnClickListener {
            checkAnswer(listCards[2],id_game_image3)
        }

        id_game_image4.setOnClickListener {
            checkAnswer(listCards[3],id_game_image4)
        }

        id_game_image5.setOnClickListener {
            checkAnswer(listCards[4],id_game_image5)
        }

        id_game_image6.setOnClickListener {
            checkAnswer(listCards[5],id_game_image6)
        }

        id_game_image7.setOnClickListener {
            checkAnswer(listCards[6],id_game_image7)
        }

        id_game_image8.setOnClickListener {
            checkAnswer(listCards[7],id_game_image8)
        }

        id_game_image9.setOnClickListener {
            checkAnswer(listCards[8],id_game_image9)
        }


        id_game_button_restart.setOnClickListener {
            if(checkMoney()){
                id_game_button_restart.isVisible = false
                id_game_ll.isVisible = true
                MAIN.minusCash(50)
                getRandomCards()
                loadRandomCards()
            }else{
                Toast.makeText(requireContext(),"you don't have enough money",Toast.LENGTH_SHORT).show()
            }
        }







    }

    private fun getRandomCards(){
        listCards = listAllCard.shuffled().slice(0..8)
        hiddenCard = listCards.shuffled()[0]
    }

    private fun loadRandomCards(){
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(requireContext(),"remember the map locations",Toast.LENGTH_SHORT).show()
            clickableOff()
            Glide.with(requireContext())
                .load(url+listCards[0])
                .into(id_game_image1)
            Glide.with(requireContext())
                .load(url+listCards[1])
                .into(id_game_image2)
            Glide.with(requireContext())
                .load(url+listCards[2])
                .into(id_game_image3)
            Glide.with(requireContext())
                .load(url+listCards[3])
                .into(id_game_image4)
            Glide.with(requireContext())
                .load(url+listCards[4])
                .into(id_game_image5)
            Glide.with(requireContext())
                .load(url+listCards[5])
                .into(id_game_image6)
            Glide.with(requireContext())
                .load(url+listCards[6])
                .into(id_game_image7)
            Glide.with(requireContext())
                .load(url+listCards[7])
                .into(id_game_image8)
            Glide.with(requireContext())
                .load(url+listCards[8])
                .into(id_game_image9)
            closedHiddenCard()
            delay(time)
            clickableOn()
            closedRandomCard()
            loadHiddenCard()
        }
    }

    private fun closedRandomCard(){
        Glide.with(requireContext())
            .load(URL_PICTURE_OBRATNAYA_STORONA)
            .into(id_game_image1)
        Glide.with(requireContext())
            .load(URL_PICTURE_OBRATNAYA_STORONA)
            .into(id_game_image2)
        Glide.with(requireContext())
            .load(URL_PICTURE_OBRATNAYA_STORONA)
            .into(id_game_image3)
        Glide.with(requireContext())
            .load(URL_PICTURE_OBRATNAYA_STORONA)
            .into(id_game_image4)
        Glide.with(requireContext())
            .load(URL_PICTURE_OBRATNAYA_STORONA)
            .into(id_game_image5)
        Glide.with(requireContext())
            .load(URL_PICTURE_OBRATNAYA_STORONA)
            .into(id_game_image6)
        Glide.with(requireContext())
            .load(URL_PICTURE_OBRATNAYA_STORONA)
            .into(id_game_image7)
        Glide.with(requireContext())
            .load(URL_PICTURE_OBRATNAYA_STORONA)
            .into(id_game_image8)
        Glide.with(requireContext())
            .load(URL_PICTURE_OBRATNAYA_STORONA)
            .into(id_game_image9)
    }

    private fun loadHiddenCard(){
        Glide.with(requireContext())
            .load(url+hiddenCard)
            .into(id_game_image10)
    }
    private fun closedHiddenCard(){
        Glide.with(requireContext())
            .load(URL_PICTURE_OBRATNAYA_STORONA)
            .into(id_game_image10)
    }

    private fun clickableOff(){
        id_game_image1.isClickable = false
        id_game_image2.isClickable = false
        id_game_image3.isClickable = false
        id_game_image4.isClickable = false
        id_game_image5.isClickable = false
        id_game_image6.isClickable = false
        id_game_image7.isClickable = false
        id_game_image8.isClickable = false
        id_game_image9.isClickable = false
    }
    private fun clickableOn(){
        id_game_image1.isClickable = true
        id_game_image2.isClickable = true
        id_game_image3.isClickable = true
        id_game_image4.isClickable = true
        id_game_image5.isClickable = true
        id_game_image6.isClickable = true
        id_game_image7.isClickable = true
        id_game_image8.isClickable = true
        id_game_image9.isClickable = true
    }

    private fun checkAnswer(item_listCards:String,imageView: ImageView){
        if(hiddenCard==item_listCards){
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(requireContext(),"right!",Toast.LENGTH_SHORT).show()
                correct+=1
                id_game_tv_correct.text = "correct: $correct"
                Glide.with(requireContext())
                    .load(url+item_listCards)
                    .into(imageView)
                delay(2000)
                getRandomCards()
                loadRandomCards()
            }
        }else{
            CoroutineScope(Dispatchers.Main).launch {
                clickableOff()
                Toast.makeText(requireContext(),"wrong!",Toast.LENGTH_SHORT).show()
                id_game_button_restart.isVisible = true
                id_game_ll.isVisible = false
                checkRecord()
                Glide.with(requireContext())
                    .load(url+item_listCards)
                    .into(imageView)
            }
        }
    }

    private fun checkRecord(){
        when(requireArguments().getString(COMPLEXITY)){
            EASY -> {
                if(correct> MAIN.getRecordEasy()){
                    Toast.makeText(requireContext(),"you have broken the record",Toast.LENGTH_SHORT).show()
                    MAIN.setRecordEasy(correct)
                }
            }
            MEDIUM -> {
                if(correct> MAIN.getRecordMedium()){
                    Toast.makeText(requireContext(),"you have broken the record",Toast.LENGTH_SHORT).show()
                    MAIN.setRecordMedium(correct)
                }
            }
            HARD -> {
                if(correct> MAIN.getRecordHard()){
                    Toast.makeText(requireContext(),"you have broken the record",Toast.LENGTH_SHORT).show()
                    MAIN.setRecordHard(correct)
                }
            }
        }
    }


    private fun checkMoney(): Boolean {
        return MAIN.getMyCash()>50
    }






}