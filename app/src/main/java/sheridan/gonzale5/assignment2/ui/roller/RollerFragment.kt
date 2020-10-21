package sheridan.gonzale5.assignment2.ui.roller

import android.os.Bundle
import android.view.*
import android.widget.Toast
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
    private val die = Die()
    private var total: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRollerBinding.inflate(inflater, container, false)

        binding.rollButton.setOnClickListener { save() }

        // show output when the data is saved in the database

        viewModel.state.observe(viewLifecycleOwner){
            if(it.status == RollerViewModel.Status.SAVED_DATA) showOutput(it.gameScoreId!!)
        }

        return binding.root
    }

    private fun save(){
        // get die one value
        val die_one = Integer.parseInt(binding.dieOne.text.toString())
        // get die two value
        val die_two = Integer.parseInt(binding.dieTwo.text.toString())
        // get die three value
        val die_three = Integer.parseInt(binding.dieThree.text.toString())

        total = die_one + die_two + die_three

        viewModel.save(GameScore(0, die_one, die_two, die_three, total))
    }

    private fun showOutput(gameScoreId: Long) {

        viewModel.reset() // prevents going more than once

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

    private fun rollDice() {
        die.roll()
        displayDice()
    }

    private fun displayDice() {
        binding.resultText.text =
            if (die.value > 0)
                die.value.toString()
            else " "
    }

}