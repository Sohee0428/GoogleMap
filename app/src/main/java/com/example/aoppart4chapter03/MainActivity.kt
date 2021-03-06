package com.example.aoppart4chapter03

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.aoppart4chapter03.MapActivity.Companion.SEARCH_RESULT_EXTRA_KEY
import com.example.aoppart4chapter03.databinding.ActivityMainBinding
import com.example.aoppart4chapter03.model.LocationLatLngEntity
import com.example.aoppart4chapter03.model.SearchResultEntity
import com.example.aoppart4chapter03.response.search.Poi
import com.example.aoppart4chapter03.response.search.Pois
import com.example.aoppart4chapter03.utility.RetrofitUtil
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SearchRecyclerviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        job = Job()

        initAdapter()
        initViews()
        bindViews()
        initData()

    }

    private fun initAdapter() {
        adapter = SearchRecyclerviewAdapter()
    }

    private fun initViews() = with(binding) {
        emptyResultTxt.isVisible = false
        recycleView.adapter = adapter
    }

    private fun bindViews() = with(binding) {
        searchBtn.setOnClickListener {
            searchKeyword(searchBarInputEtx.text.toString())
        }
    }

    private fun initData() {
//        빈 상태로 설정
        adapter.notifyDataSetChanged()
    }

    private fun setData(pois: Pois) {
        val dataList = pois.poi.map {
            SearchResultEntity(
                name = it.name ?: "빌딩명 없음",
                fullAddress = makeMainAddress(it),
                locationLatLng = LocationLatLngEntity(
                    it.noorLat.toFloat(),
                    it.noorLon.toFloat()
                )
            )
        }
        adapter.setSearchResultList(dataList) {
            Toast.makeText(
                this,
                "빌딩 이름 : ${it.name}, 주소 : ${it.fullAddress}, 위도/경도 : ${it.locationLatLng}",
                Toast.LENGTH_SHORT
            )
                .show()
            startActivity(
                Intent(this, MapActivity::class.java).apply {
                    putExtra(SEARCH_RESULT_EXTRA_KEY, it)
                }
            )
        }
    }

    private fun searchKeyword(ketwordString: String) {
//        어느 화면에서 시작할지
        launch(coroutineContext) {
            try {
//                IOThread로 바꿨다가
                withContext(Dispatchers.IO) {
                    val response = RetrofitUtil.apiService.getSearchLocation(
                        keyword = ketwordString
                    )
                    if (response.isSuccessful) {
//                        성공 시에는 MainThread로 바꿔주기
                        val body = response.body()
                        withContext(Dispatchers.Main) {
                            Log.e("response", body.toString())
                            body?.let { searchResponse ->
                                setData(searchResponse.searchPoiInfo.pois)
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun makeMainAddress(poi: Poi): String =
        if (poi.secondNo?.trim().isNullOrEmpty()) {
            (poi.upperAddrName?.trim() ?: "") + " " +
                    (poi.middleAddrName?.trim() ?: "") + " " +
                    (poi.lowerAddrName?.trim() ?: "") + " " +
                    (poi.detailAddrname?.trim() ?: "") + " " +
                    poi.firstNo?.trim()
        } else {
            (poi.upperAddrName?.trim() ?: "") + " " +
                    (poi.middleAddrName?.trim() ?: "") + " " +
                    (poi.lowerAddrName?.trim() ?: "") + " " +
                    (poi.detailAddrname?.trim() ?: "") + " " +
                    (poi.firstNo?.trim() ?: "") + " " +
                    poi.secondNo?.trim()
        }
}