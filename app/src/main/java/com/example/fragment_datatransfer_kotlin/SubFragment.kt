package com.example.fragment_datatransfer_kotlin

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.fragment_datatransfer_kotlin.databinding.FragmentSubBinding

class SubFragment : Fragment() {
    private lateinit var binding : FragmentSubBinding
    private val viewModel by activityViewModels<MainViewModel>()
    var data = "초기값"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sub, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.text = arguments?.getString("text", "null")

        binding.button.setOnClickListener {
            data = "텍스트 변경값2"
            viewModel.data = "뷰모델 변경값"
            val action = SubFragmentDirections.actionSubFragmentToMainFragment(data)
            findNavController().navigate(action)
        }
    }

}