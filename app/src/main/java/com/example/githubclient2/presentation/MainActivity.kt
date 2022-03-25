package com.example.githubclient2.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubclient2.R
import com.example.githubclient2.presentation.main.fragment.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment()).commit()
        }

    }

}