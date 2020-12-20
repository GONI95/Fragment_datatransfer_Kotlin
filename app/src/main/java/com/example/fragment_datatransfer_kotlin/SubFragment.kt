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
    private lateinit var binding: FragmentSubBinding
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sub, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var argument_text = arguments?.getString("text", "null")
        binding.button.text = argument_text
        // MainFragment에서 어떤 버튼을 눌렀나에 따라 값이 달라짐

        binding.button.setOnClickListener {
                //viewModel.data = "뷰모델 변경값"
                val action = SubFragmentDirections.actionSubFragmentToMainFragment(argument_text.toString())
                // Main에서 무엇을 눌렀냐에 따라 값이 달라짐
                findNavController().navigate(action)
        }
    }

}