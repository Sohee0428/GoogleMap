package com.example.aoppart4chapter03

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.aoppart4chapter03.databinding.ActivityMainBinding
import com.example.aoppart4chapter03.model.LocationLatLngEntity
import com.example.aoppart4chapter03.model.SearchResultEntity
import com.example.aoppart4chapter03.response1.search.Poi
import com.example.aoppart4chapter03.response1.search.Pois
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

    }

    private fun initAdapter() {
        adapter = SearchRecyclerviewAdapter{
            Toast.makeText(this, "아이템 클릭", Toast.LENGTH_SHORT).show()
        }
    }



    private fun searchKeyword(ketwordString: String) {
        launch(coroutineContext) {
            try {
                withContext(Dispatchers.IO) {
                    val response = RetrofitUtil.apiService.getSearchLocation(
                        keyword = ketwordString
                    )
                    if (response.isSuccessful) {
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

}