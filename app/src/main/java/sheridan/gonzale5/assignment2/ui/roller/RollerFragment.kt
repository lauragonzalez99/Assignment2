package sheridan.gonzale5.assignment2.ui.roller

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import sheridan.gonzale5.assignment2.R
import sheridan.gonzale5.assignment2.databinding.FragmentRollerBinding
import sheridan.gonzale5.assignment2.database.GameScore
import sheridan.gonzale5.assignment2.model.Die

class RollerFragment : Fragment() {

    private lateinit var binding: FragmentRollerBinding
    private val viewModel: RollerViewModel by viewModels()
    private val dieOne = Die()
    private val dieTwo = Die()
    private val dieThree = Die()
    private var total: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRollerBinding.inflate(inflater, container, false)

        binding.rollButton.setOnClickListener { rollDice() }

        return binding.root
    }

    private fun rollDice() {
        dieOne.roll()
        dieTwo.roll()
        dieThree.roll()
        displayDice()
    }

    private fun displayDice() {
        binding.dieOne.text = dieOne.value.toString()
        binding.dieTwo.text = dieTwo.value.toString()
        binding.dieThree.text = dieThree.value.toString()

        total =  dieOne.value + dieTwo.value + dieThree.value

        binding.resultText.text = getString(R.string.sum_number, total)

        save()
    }

    private fun save(){
        viewModel.save(GameScore(0, dieOne.value, dieTwo.value, dieThree.value, total))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_roller, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.action_history -> {
                findNavController().navigate(R.id.action_global_to_history)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}