package ru.test.nytn.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.test.nytn.databinding.FragmentNewsPageBinding
import ru.test.nytn.recyclerView.NewsAdapter
import ru.test.nytn.recyclerView.NewsListener
import ru.test.nytn.viewModels.NewsViewModel

@AndroidEntryPoint
class NewsPageFragment : Fragment() {

    private lateinit var binding: FragmentNewsPageBinding
    private lateinit var newsAdapter : NewsAdapter

    private val viewModel : NewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initBinding(inflater, container)
        initAdapter()
        setupObservers()
        setupOnClickListener()
        getNews()

        return binding.root
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentNewsPageBinding.inflate(inflater, container, false)
    }

    private fun initAdapter() {
        newsAdapter = NewsAdapter(object : NewsListener {
            override fun readMore(webUrl: String) {
                openBrowser(webUrl)
            }
        })
        binding.newsList.adapter = newsAdapter
    }

    private fun setupObservers() {

        viewModel.newsList.observe(viewLifecycleOwner) {
            newsAdapter.submitList(it)
        }

        viewModel.appState.observe(viewLifecycleOwner) { state ->
            binding.apply {
                newsGroup.isVisible = state.idle
                progressBar.isVisible = state.loading
                errorText.isVisible = state.error
                emptyListText.isVisible = state.emptyList
                retryButton.isVisible = state.error || state.emptyList
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {errorId ->
            errorId?.let {
                Toast.makeText(requireContext(), getString(it), Toast.LENGTH_SHORT).show()
                viewModel.errorIsShow()
            }
        }

    }

    private fun setupOnClickListener() {
        binding.nytLogo.setOnClickListener {
            openBrowser(viewModel.getNYTLink())
        }
        binding.retryButton.setOnClickListener {
            viewModel.getNewsList()
        }
    }

    private fun getNews() {
        viewModel.getNewsList()
    }

    private fun openBrowser(url : String) {
        val browserActivity = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserActivity)
    }
}