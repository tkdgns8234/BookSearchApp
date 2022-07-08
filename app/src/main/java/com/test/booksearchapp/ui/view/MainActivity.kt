package com.test.booksearchapp.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.test.booksearchapp.R
import com.test.booksearchapp.data.repository.BookSearchRepositoryImpl
import com.test.booksearchapp.databinding.ActivityMainBinding
import com.test.booksearchapp.ui.viewmodel.BookSearchViewModel
import com.test.booksearchapp.ui.viewmodel.BookSearchViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    // lazy 사용 시 참조되는 시점에 초기화됨 late val 은 나중에 초기화
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var bookSearchViewModel: BookSearchViewModel
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupJetpackNavigation()
        setupActionBar()

        val bookSearchRepository = BookSearchRepositoryImpl()
        val factory = BookSearchViewModelProviderFactory(bookSearchRepository, this)
        bookSearchViewModel = ViewModelProvider(this, factory)[BookSearchViewModel::class.java]
    }

    // action bar를 navcontroller와 연결
    private fun setupActionBar() {
        appBarConfiguration = AppBarConfiguration(setOf(R.id.fragment_search, R.id.fragment_favorite, R.id.fragment_settings))
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    // navigation 될 때 호출됨, appbarconfiguration에따라 actionbar 설정
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun setupJetpackNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.booksearch_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        //bottom navigation에 navcontroller 연결
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}