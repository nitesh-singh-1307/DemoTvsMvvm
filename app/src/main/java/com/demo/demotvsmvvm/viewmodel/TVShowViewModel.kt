package com.demo.demotvsmvvm.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.demo.demotvsmvvm.models.TvShow
import com.demo.demotvsmvvm.models.TvShows
import com.demo.demotvsmvvm.retrofit.RestClientApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TVShowViewModel(application: Application) : AndroidViewModel(application) {
    val tvshowsdata: MutableLiveData<TvShows> = MutableLiveData()
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)



    fun getAllTvShow(): MutableLiveData<TvShows> {
        try {
            coroutineScope.launch(Dispatchers.IO) {
                val response = RestClientApi.getClient().apiTvShowAsync(1).await()
                Log.e("check_tvshowdata", response.message())
                Log.e("check_tvshowdata", "" +  response.isSuccessful)
                Log.e("check_tvshowdata", "" +  response.code())
                Log.e("check_tvshowdata", "" +  response.body())

                if (response.code() == 200 && response.body() != null) {
                        tvshowsdata.postValue(response.body())
                    Log.e("check_tvshowdata", "" +  response.body())

                }
            }
            return tvshowsdata
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return tvshowsdata
    }


}