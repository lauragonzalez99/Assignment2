package sheridan.gonzale5.assignment2.ui.history

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import sheridan.gonzale5.assignment2.R
import sheridan.gonzale5.assignment2.database.GameScore
import sheridan.gonzale5.assignment2.databinding.FragmentHistoryBinding

/**
 * A fragment representing a list of Items.
 */
class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding

    private lateinit var adapter: HistoryRecyclerViewAdapter

    private val viewModel: HistoryViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        // make the adapter
        adapter = HistoryRecyclerViewAdapter(requireContext())

        with(binding){
            val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            recyclerView.addItemDecoration(divider)
            recyclerView.adapter = adapter
        }

        viewModel.history.observe(viewLifecycleOwner){ refreshHistory(it) }

        navController = findNavController()

        return binding.root
    }

    private fun refreshHistory(list: List<GameScore>?) {
        adapter.history = list
        var historyTotal = 0
        if (list != null) {
            for (score in list) {
                historyTotal += score.total
            }
        }
        binding.historyTotal.text = resources.getString(R.string.history_total, historyTotal)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_history, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.action_clear -> {
                clear()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun clear(){
        viewModel.clear()
    }

}