package com.example.githubclient2.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.githubclient2.R
import com.example.githubclient2.presentation.fragments.details.fragment.DetailsFragment
import com.example.githubclient2.presentation.fragments.main.fragment.MainFragment
import com.example.githubclient2.utils.FragmentTitleResource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment()).commit()
        }

        supportFragmentManager.addOnBackStackChangedListener {
            updateActionBar()
        }
    }

    private fun updateActionBar() {
        val showUp = supportFragmentManager.backStackEntryCount > 0
        supportActionBar?.setDisplayHomeAsUpEnabled(showUp)
        for(fragment in supportFragmentManager.fragments){
            if(fragment.isVisible && fragment != null){
                when(fragment){
                    is MainFragment -> supportActionBar?.title = getString(R.string.main_fragment_title)
                    is DetailsFragment -> supportActionBar?.title = FragmentTitleResource.title
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            supportFragmentManager.popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

}