package com.example.rithviknakirikanti_newsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rithviknakirikanti_newsapp.databinding.FragmentNewsDetailBinding

class NewsDetailFragment : Fragment() {
    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = arguments?.getSerializable("article") as? Article
        article?.let {
            binding.articleTitle.text = it.title
            binding.articleDescription.text = it.description ?: "Description not available"
            binding.articleAuthor.text = it.author ?: "Author not available"
            binding.articlePublishedAt.text = it.publishedAt
        }

        binding?.backButton?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(article: Article): NewsDetailFragment {
            val args = Bundle().apply {
                putSerializable("article", article)
            }
            return NewsDetailFragment().apply {
                arguments = args
            }
        }
    }
}
