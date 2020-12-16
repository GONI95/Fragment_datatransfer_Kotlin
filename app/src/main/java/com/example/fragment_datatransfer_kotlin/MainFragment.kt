package com.example.fragment_datatransfer_kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.fragment_datatransfer_kotlin.databinding.FragmentMainBinding

class MainFragment : Fragment() {
//onCreateView()를 지우고 Fragment() ->(R.layout.fragment_main)로 변경도 가능하다.
    private lateinit var binding : FragmentMainBinding
    private val viewModel by activityViewModels<MainViewModel>()
    var text = "텍스트 초기값"
    // MainFragment가 포함된 Activity의 ViewModel을 쓰기 떄문에 MainActivity의 lifecycle에 따라 작동됨

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        /** inflate : 레이아웃을 확장하고 해당 레이아웃에 대해 새로 생성된 바인딩을 반환
         *  inflater : 바인딩 레이아웃을 확장하는데 사용되는 LayoutInflater
         *  LayoutId : 확장할 레이아웃의 리소스 ID
         *  parent : (ViewGruop) 생성된 계층의 부모가되는 선택적 뷰(attachToParent true 경우)
         *  반환된 계층의 루트에 대한 LayoutParams 값 집합을 제공하는 객체(attachToParent false 경우)
         *  attachToParent : (boolean) 확장된 계층 구조가 상위 매개변수에 첨부될지 여부를 확인
         *  false - parent는 xml의 루트 뷰에 대한 LayoutParams의 올바른 하위 클래스를 만드는데 사용
         */
        binding.lifecycleOwner = this
        // binding이 어느 activity, fragment 등의 생명주기를 따를 것인지 설정
        binding.myViewmodel = viewModel
        // layout의 viewModel과 MainViewModel을 액세스하여 xml이 activity에 연결을 설정
        return binding.root
        // binding과 관련된 layout 파일의 가장 바깥 뷰 or 병합 layout인 경우 첫 번째 루트 반환
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToSubFragment(viewModel.data)
            findNavController().navigate(action)
        }

        binding.button2.text = arguments?.getString("data") ?: "Null"

        binding.button2.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToSubFragment(text)
            findNavController().navigate(action)
        }

    }
}



//private lateinit var binding : FragmentMainBinding

// private val viewModel by viewModels<MainViewModel>()  // 방식 a.

// private lateinit var viewModel : MainViewModel  // 방식 b.
// viewModel = ViewModelProvider(this).get(MainViewModel::class.java)  // 방식 b.

//binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

// binding.lifecycleOwner = this
// 공통(주인의 lifecycle을 따라 작업이 이루어짐), (data를 xmldl 관찰하다 변경하도록 할 수 있음)
// binding.viewModel = viewModel
// 공통(binding 객체에 설정된 xml로 전달됨, xml에 <data> 선언해줘야함)
// 문제 1. 뷰모델 없이 activity에서 binding으로 xml에서 데이터를 관찰, 메소드 호출이 가능한지 확인해보자
// 답 : ViewModel 없이도 코드만 제대로 짜면 문제없이 잘 수행된다.
