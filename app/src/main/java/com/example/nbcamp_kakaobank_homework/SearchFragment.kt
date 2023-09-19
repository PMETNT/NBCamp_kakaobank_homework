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
import com.example.nbcamp_kakaobank_homework.databinding.FragmentSearchBinding
import com.example.nbcamp_kakaobank_homework.image_data.Document
import com.example.nbcamp_kakaobank_homework.image_data.ImageSearch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
            Toast.makeText(requireContext(), "통신 성공!", Toast.LENGTH_SHORT)
            searchKeyword(
                binding.etSearch.text.toString()
            )


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

        val api: KakaoImageSearchAPI =
            retrofit.create(KakaoImageSearchAPI::class.java)

//        CoroutineScope(Dispatchers.IO).launch {
//
//            launch(Dispatchers.Main) {
//
//            }
//
//        }


        val call = api.getImageSearchKeyword(API_info.API_KEY, keyword)

        call.enqueue(object : Callback<ImageSearch> {
            override fun onResponse(
                call: Call<ImageSearch>,
                response: Response<ImageSearch>
            ) {
                adapter.homework_imageList = response.body() as ImageSearch
                adapter.notifyDataSetChanged()

                Toast.makeText(requireContext(), "통신 성공!", Toast.LENGTH_SHORT)
            }

            override fun onFailure(call: Call<ImageSearch>, t: Throwable) {
                Toast.makeText(requireContext(), "통신 실패!", Toast.LENGTH_SHORT)
            }
        })
    }


}


