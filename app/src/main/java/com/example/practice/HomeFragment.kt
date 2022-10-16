package com.example.practice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val button = view.findViewById<Button>(R.id.button)
        var num = 0
        view.findViewById<TextView>(R.id.result).apply {
            text = requireArguments().getInt("dataId").toString()
            if (text == "0"){
                num = 0
            } else {
                num = requireArguments().getInt("dataId") + 1
            }
        }
        button.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_dataFragment, Bundle().apply {
                putInt("itemid", num)
            })
        }
        return view
    }
}