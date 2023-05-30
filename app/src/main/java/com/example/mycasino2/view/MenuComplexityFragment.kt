package com.example.mycasino2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mycasino2.*
import kotlinx.android.synthetic.main.fragment_menu_complexity.*

class MenuComplexityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_complexity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id_comp_tv_record_easy.text = "your record: ${MAIN.getRecordEasy()}"
        id_comp_tv_record_medium.text = "your record: ${MAIN.getRecordMedium()}"
        id_comp_tv_record_hard.text = "your record: ${MAIN.getRecordHard()}"

        var complexity = ""

        id_comp_button_easy.setOnClickListener {
            complexity = EASY
            id_image_easy_done.setBackgroundResource(R.drawable.done)
            id_image_medium_done.setBackgroundResource(0)
            id_image_hard_done.setBackgroundResource(0)
        }

        id_comp_button_medium.setOnClickListener {
            complexity = MEDIUM
            id_image_medium_done.setBackgroundResource(R.drawable.done)
            id_image_easy_done.setBackgroundResource(0)
            id_image_hard_done.setBackgroundResource(0)
        }

        id_comp_button_hard.setOnClickListener {
            complexity = HARD
            id_image_hard_done.setBackgroundResource(R.drawable.done)
            id_image_medium_done.setBackgroundResource(0)
            id_image_easy_done.setBackgroundResource(0)
        }

        id_comp_button_next.setOnClickListener {
            var bundle = Bundle()
            bundle.putString(COMPLEXITY,complexity)
            MAIN.navController.navigate(R.id.action_menuComplexityFragment_to_gameFragment,bundle)
        }

    }

}