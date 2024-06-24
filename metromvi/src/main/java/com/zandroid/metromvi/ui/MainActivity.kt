package com.zandroid.metromvi.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.zandroid.metromvi.R
import com.zandroid.metromvi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //Binding
    private  var _binding: ActivityMainBinding?=null
    private  val binding get() = _binding!!

   private lateinit var navHost:NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHost=supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}