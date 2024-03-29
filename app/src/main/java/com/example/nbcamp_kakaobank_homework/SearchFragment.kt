package com.example.nbcamp_kakaobank_homework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbcamp_kakaobank_homework.api.API_info
import com.example.nbcamp_kakaobank_homework.api.KakaoImageSearchAPI
import com.example.nbcamp_kakaobank_homework.api.KakaoVclipSearchAPI
import com.example.nbcamp_kakaobank_homework.databinding.FragmentSearchBinding
import com.example.nbcamp_kakaobank_homework.image_data.Document
import com.example.nbcamp_kakaobank_homework.image_data.ImageSearch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val adapter = SearchAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.recyclerViewSearch.adapter = adapter
        binding.recyclerViewSearch.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.btnSearch.setOnClickListener {

            val keyword = binding.etSearch.text.toString()

            searchKeyword(keyword)


        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun searchKeyword(keyword: String) {
        val retrofit =
            Retrofit.Builder()
                .baseUrl(API_info.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api: KakaoImageSearchAPI = retrofit.create()
        val api2: KakaoVclipSearchAPI = retrofit.create()  // 동영상 검색 1차


        try {
            CoroutineScope(Dispatchers.IO).launch {
                val response = api.getImageSearchKeyword(API_info.API_KEY, keyword, "recency")
                val response2 = api2.getVclipSearchKeyword(API_info.API_KEY, keyword, "recency") // 동영상 검색 1차


                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response2.isSuccessful) {
                        adapter.homework_imageList = response.body() as ImageSearch

                        // 동영상 검색 1차
                        adapter.homework_imageList!!.documents.addAll(
                            response2.body()!!.documents
                        )

                        adapter.homework_imageList!!.documents.sortByDescending { it.datetime }
                        adapter.notifyDataSetChanged()
                    }
                }


            }


        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


}


