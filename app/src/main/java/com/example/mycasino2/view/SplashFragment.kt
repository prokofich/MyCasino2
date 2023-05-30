package com.example.mycasino2.view

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mycasino2.*
import com.example.mycasino2.viewmodel.SplashViewModel
import kotlinx.android.synthetic.main.fragment_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext())
            .load(URL_PICTURE_CARDS)
            .into(id_splash_image_cards)

        id_splash_progress.max = 1000
        val finishProgress = 1000

        ObjectAnimator.ofInt(id_splash_progress,"progress",finishProgress)
            .setDuration(3000)
            .start()

        val splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]

        var namePhone = Build.MODEL.toString()
        var locale = Locale.getDefault().getDisplayLanguage().toString()
        var id = namePhone+locale+"id"


        splashViewModel.setPostParametersPhone(namePhone,locale,id)
        splashViewModel.webViewUrl.observe(viewLifecycleOwner){ responce ->
            when(responce.body()!!.url){
                "no" -> { goToMain("no") }
                "nopush" -> { goToMain("nopush") }
                else -> { goToWeb(responce.body()!!.url)}
            }
        }

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun goToMain(valueToKey:String){
        CoroutineScope(Dispatchers.Main).launch {
            var bundle = Bundle()
            bundle.putString("key",valueToKey)
            delay(3000)
            if(MAIN.getMyName()!=""){
                if(checkDay()){
                    MAIN.navController.navigate(R.id.action_splashFragment_to_dailyPrizeFragment,bundle)
                }else{
                    MAIN.navController.navigate(R.id.action_splashFragment_to_menuFragment,bundle)
                }
            }else{
                MAIN.navController.navigate(R.id.action_splashFragment_to_registrFragment,bundle)
            }
        }
    }

    private fun goToWeb(url:String){
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            var intent = Intent(requireContext(), WebViewActivity::class.java)
            intent.putExtra("key",url)
            startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkDay():Boolean{
        return LocalDate.now().toString() != MAIN.getLastDay()
    }




}