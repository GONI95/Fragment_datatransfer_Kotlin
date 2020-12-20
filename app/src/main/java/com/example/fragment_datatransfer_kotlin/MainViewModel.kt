package com.example.fragment_datatransfer_kotlin

import androidx.lifecycle.ViewModel

// MainActivity가 두 개의 Fragment를 가지고있기 때문에 하나의 ViewModel로 두 개를 공유할 수 있음
class MainViewModel : ViewModel() {
    var data : String = "ViewModel 초기값"
    
}