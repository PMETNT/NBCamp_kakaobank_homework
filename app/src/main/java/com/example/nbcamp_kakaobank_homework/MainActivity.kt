package com.example.nbcamp_kakaobank_homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nbcamp_kakaobank_homework.databinding.ActivityMainBinding
import com.example.nbcamp_kakaobank_homework.image_data.Document
import com.example.nbcamp_kakaobank_homework.image_data.ImageSearch
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 뷰 페이져2 설정
        val usedList = listOf(
            SearchFragment(),
            MyBoxFragment()
        )

        val adapter = FragmentAdapter(this)
        adapter.fragmentList = usedList
        binding.viewPagerHomework.adapter = adapter


        // 탭 레이아웃 설정
        val tabTitles = listOf<String>("검색", "보관함")
        TabLayoutMediator(binding.tapLayoutHomework, binding.viewPagerHomework) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()


    }

}