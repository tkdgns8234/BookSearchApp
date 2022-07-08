package com.test.booksearchapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.test.booksearchapp.databinding.FragmentBookDetailBinding

class BookDetailFragment : Fragment() {
    private var _binding : FragmentBookDetailBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<BookDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val book = args.book
        binding.wvBookDetail.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(book.url)
        }
    }

    // fragment 의 생명주기에따라 webView도 동일하게 동작하도록 설정
    override fun onPause() {
        binding.wvBookDetail.onPause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        binding.wvBookDetail.onResume()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}