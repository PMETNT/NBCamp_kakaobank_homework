package com.example.nbcamp_kakaobank_homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.nbcamp_kakaobank_homework.Utils.getListPref
import com.example.nbcamp_kakaobank_homework.Utils.setListPref
import com.example.nbcamp_kakaobank_homework.databinding.ActivityMainBinding
import com.example.nbcamp_kakaobank_homework.image_data.Document
import com.example.nbcamp_kakaobank_homework.model.Bookmark
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.d("jiseong1","처음 ${Bookmark.bookmarkList} 비어있는게 맞음")

        var storedBookmarkList = getListPref(this, "bookmark")
        Log.d("jiseong1","${storedBookmarkList}")
        Bookmark.bookmarkList = storedBookmarkList
        bookmark_adapter.notifyDataSetChanged()

        Log.d("jiseong1","${Bookmark.bookmarkList}")









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

    override fun onPause() {
        super.onPause()
        setListPref(this, "bookmark", Bookmark.bookmarkList)
        Log.d("jiseong1","저장하고 종료했습니당")
    }




}

