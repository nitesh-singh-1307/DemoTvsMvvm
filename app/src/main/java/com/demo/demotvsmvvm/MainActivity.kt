package com.demo.demotvsmvvm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.demo.demotvsmvvm.databinding.ActivityMainBinding
import com.demo.demotvsmvvm.viewmodel.TVShowViewModel
import okhttp3.internal.wait

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var tvShowViewModel: TVShowViewModel

    //private lateinit var
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        setContentView(R.layout.activity_main)
        tvShowViewModel = ViewModelProvider(this@MainActivity).get(TVShowViewModel::class.java)

        observeViewModel()
    }

    private fun observeViewModel() {
        tvShowViewModel.getAllTvShow().observe(this, Observer {
            Log.e("check_response_data", "*************************" + it)

        })

    }
}