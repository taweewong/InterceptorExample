package com.taweewong.interceptorexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.taweewong.interceptorexample.api.ApiManager
import com.taweewong.interceptorexample.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            callService()
        }
    }

    private fun callService() {
        val call = ApiManager().potter().getHouseFromSortingHat()
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    textView.text = response.body()
                } else {
                    textView.text = response.message()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                textView.text = t.message
            }
        })
    }
}
