package com.example.rithviknakirikanti_newsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.rithviknakirikanti_newsapp.databinding.ListItemNewsBinding

class ArticleHolder(
    private val binding: ListItemNewsBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(article: Article) {
        binding.newsTitle.text = article.title
        binding.newsDate.text = article.publishedAt
       // binding.newsDescription.text = article.description ?: "Description not available"

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${article.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class NewsListAdapter(
    private var articles: List<Article>,
    private val onArticleClicked: (Article) -> Unit
) : RecyclerView.Adapter<ArticleHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemNewsBinding.inflate(inflater, parent, false)
        return ArticleHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
        holder.itemView.setOnClickListener { onArticleClicked(article) }
    }

    fun updateArticles(newArticles: List<Article>) {
        this.articles = newArticles
        notifyDataSetChanged()
    }

    override fun getItemCount() = articles.size
}
