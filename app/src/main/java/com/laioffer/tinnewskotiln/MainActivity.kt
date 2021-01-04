package com.laioffer.tinnewskotiln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.laioffer.tinnewskotiln.model.NewsResponse
import com.laioffer.tinnewskotiln.network.NewsApi
import com.laioffer.tinnewskotiln.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_save
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

//        Those code will be replaced by coroutines and livedata
        val api = RetrofitClient.newInstance(this).create(NewsApi::class.java)
        api.getTopHeadlines("US").enqueue(object : Callback<NewsResponse> {
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d("getTopHeadlines", t.toString());
            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    if (response.isSuccessful) {
                        Log.d("getTopHeadlines", response.body().toString());
                    } else {
                        Log.d("getTopHeadlines", response.toString());
                    }
                }
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}