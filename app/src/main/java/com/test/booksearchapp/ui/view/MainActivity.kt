package com.test.booksearchapp.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setBottomNavigationview()
        // 액티비티가 처음 실행되었을때만 실행
        if (savedInstanceState == null) {
            binding.bottomNavigationView.selectedItemId = R.id.item_search
        }

        val bookSearchRepository = BookSearchRepositoryImpl()
        val factory = BookSearchViewModelProviderFactory(bookSearchRepository, this)
        bookSearchViewModel = ViewModelProvider(this, factory)[BookSearchViewModel::class.java]
    }

    private fun setBottomNavigationview() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, SearchFragment())
                        .commit()
                    true
                }
                R.id.item_favorite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, FavoriteFragment())
                        .commit()
                    true
                }
                R.id.item_settings -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, SettingsFragment())
                        .commit()
                    true
                }

                else -> false
            }
        }
    }


}