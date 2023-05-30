package com.example.mycasino2.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.mycasino2.MAIN
import com.example.mycasino2.R
import com.example.mycasino2.URL_PICTURE_CARDS
import com.onesignal.OneSignal
import kotlinx.android.synthetic.main.fragment_registr.*
import kotlinx.android.synthetic.main.fragment_splash.*
import java.time.LocalDate

class RegistrFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registr, container, false)
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
            .load(URL_PICTURE_CARDS)
            .into(id_registr_image_cards)

        id_registr_button_next.setOnClickListener {
            if(id_registr_et_name.text.isNotEmpty()){
                MAIN.setMyName(id_registr_et_name.text.toString())
                if(checkDay()){
                    MAIN.navController.navigate(R.id.action_registrFragment_to_dailyPrizeFragment,bundle)
                }else{
                    MAIN.navController.navigate(R.id.action_registrFragment_to_menuFragment,bundle)
                }
            }else{
                Toast.makeText(requireContext(),"you didn't enter a name",Toast.LENGTH_SHORT).show()
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkDay():Boolean{
        return LocalDate.now().toString() != MAIN.getLastDay()
    }

}