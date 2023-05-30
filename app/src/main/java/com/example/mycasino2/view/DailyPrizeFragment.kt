package com.example.mycasino2.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.mycasino2.MAIN
import com.example.mycasino2.R
import com.example.mycasino2.URL_PICTURE_CASH
import com.example.mycasino2.listCash
import com.onesignal.OneSignal
import kotlinx.android.synthetic.main.fragment_daily_prize.*
import java.time.LocalDate

class DailyPrizeFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_daily_prize, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(requireArguments().getString("key")=="nopush"){
            OneSignal.disablePush(true)
        }

        var bundle = Bundle()
        bundle.putString("key",requireArguments().getString("key"))

        Glide.with(requireContext())
            .load(URL_PICTURE_CASH)
            .into(id_dailyprize_image)

        id_dailyprize_image.setOnClickListener {

            id_dailyprize_image.isVisible = false
            id_dailyprize_tv_cash.isVisible = true
            id_dailyprize_button_next.isVisible = true

            var cash = listCash.shuffled()[1]
            id_dailyprize_tv_cash.text = "${cash}$"
            MAIN.addCash(cash)
            MAIN.setCurrentDay(LocalDate.now().toString())

            Toast.makeText(requireContext(),"you have won: $cash$",Toast.LENGTH_SHORT).show()

        }

        id_dailyprize_button_next.setOnClickListener {
            MAIN.navController.navigate(R.id.action_dailyPrizeFragment_to_menuFragment,bundle)
        }

    }

}