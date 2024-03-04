import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rithviknakirikanti_newsapp.Article
import com.example.rithviknakirikanti_newsapp.NewsDetailFragment
import com.example.rithviknakirikanti_newsapp.NewsListAdapter
import com.example.rithviknakirikanti_newsapp.R
import com.example.rithviknakirikanti_newsapp.databinding.FragmentNewsListBinding

class NewsListFragment : Fragment() {

    private var binding: FragmentNewsListBinding? = null
    private val viewModel: NewsListViewModel by viewModels()
    private var newsListAdapter = NewsListAdapter(emptyList()) { article ->
        onArticleClicked(article)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsListBinding.inflate(inflater, container, false)
        binding?.newsRecyclerView?.layoutManager = LinearLayoutManager(context)
        newsListAdapter = NewsListAdapter(emptyList(), this::onArticleClicked)
        binding?.newsRecyclerView?.adapter = newsListAdapter
        setupSpinner()
        return binding!!.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun setupSpinner() {
        binding?.categorySpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCategory = parent?.getItemAtPosition(position)?.toString() ?: return
                viewModel.fetchNewsArticles(selectedCategory)
            }


            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    private fun observeViewModel() {
        viewModel.news.observe(viewLifecycleOwner) { articles ->
            newsListAdapter.updateArticles(articles)
        }
    }

    private fun onArticleClicked(article: Article) {
        val detailFragment = NewsDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable("article", article)
            }
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
