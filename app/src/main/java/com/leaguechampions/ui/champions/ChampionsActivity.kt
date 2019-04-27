package com.leaguechampions.ui.champions

import android.os.Bundle
import com.leaguechampions.R
import dagger.android.support.DaggerAppCompatActivity

class ChampionsActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_champions)
    }

//    class LoginFragment : Fragment() {
//        class LoginViewModel : ViewModel()
//
//        private val viewModel: LoginViewModel by activityViewModels()
//    }
}
