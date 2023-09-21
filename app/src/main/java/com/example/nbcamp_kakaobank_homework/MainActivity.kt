package com.example.nbcamp_kakaobank_homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nbcamp_kakaobank_homework.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 뷰 페이져2 설정
        val usedList = listOf(
            SearchFragment(),
            BookmarkFragment()
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
/** TODO() 클릭해서 해당 포지션의 데이터를 다른 리스트에 넣고,
 *  그 리스트에 있는 거가 보관함에서 리사이클러뷰로 나오고\
 *  shared 어쩌고로 데이터 보존시키면 끝?
 *
 */

