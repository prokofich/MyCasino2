package com.example.mycasino2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mycasino2.MAIN
import com.example.mycasino2.R
import com.example.mycasino2.viewmodel.TotorialViewModel
import kotlinx.android.synthetic.main.fragment_tutorial.*

class TutorialFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tutorial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tutorialViewModel = ViewModelProvider(this)[TotorialViewModel::class.java]

        tutorialViewModel.getTextInTutorial()
        tutorialViewModel.Message.observe(viewLifecycleOwner){ answer ->
            id_tutorial_tv.text = answer.body()!!.message
            id_tutorial_tv2.text = "good luck ${MAIN.getMyName()}!"
        }

    }

}