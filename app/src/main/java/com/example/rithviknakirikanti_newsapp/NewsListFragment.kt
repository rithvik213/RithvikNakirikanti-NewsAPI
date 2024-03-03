import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rithviknakirikanti_newsapp.NewsListAdapter
import com.example.rithviknakirikanti_newsapp.databinding.FragmentNewsListBinding

class NewsListFragment : Fragment() {

    private var binding: FragmentNewsListBinding? = null
    // Assuming NewsListViewModel is correctly implemented to fetch and expose LiveData<List<Article>>
    private val viewModel: NewsListViewModel by viewModels()
    // Initialize the adapter with an empty list; will be updated when data is fetched
    private val newsListAdapter = NewsListAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsListBinding.inflate(inflater, container, false)
        // Ensure your RecyclerView ID matches here
        binding?.newsRecyclerView?.layoutManager = LinearLayoutManager(context)
        // Set the adapter on creation
        binding?.newsRecyclerView?.adapter = newsListAdapter
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.news.observe(viewLifecycleOwner) { articles ->
            // Use the new method to update the adapter's dataset
            newsListAdapter.updateArticles(articles)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null // To avoid memory leaks
    }
}
