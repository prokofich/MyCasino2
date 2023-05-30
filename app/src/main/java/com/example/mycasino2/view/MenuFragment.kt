package com.example.mycasino2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mycasino2.MAIN
import com.example.mycasino2.R
import com.example.mycasino2.URL_PICTURE_CARDS
import com.onesignal.OneSignal
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(requireArguments().getString("key")=="nopush"){
            OneSignal.disablePush(true)
        }

        Glide.with(requireContext())
            .load(URL_PICTURE_CARDS)
            .into(id_menu_image_cards)

        id_menu_tv_cash.text = "your cash: ${MAIN.getMyCash()}$"

        id_menu_start_game.setOnClickListener {
            MAIN.navController.navigate(R.id.action_menuFragment_to_menuComplexityFragment)
        }

        id_menu_tutorial.setOnClickListener {
            MAIN.navController.navigate(R.id.action_menuFragment_to_tutorialFragment)
        }

        id_menu_exit.setOnClickListener {
            MAIN.finishAffinity()
        }

    }



}