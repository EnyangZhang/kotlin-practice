package nz.ac.uclive.ezh15.seng440_term1_project

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment(), RecyclerAdapter.OnItemListener {

    private val viewModel: ItemViewModel by activityViewModels(){
        ItemsViewModelFactory((activity?.application as AsUWishApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val itemAdapter = RecyclerAdapter(listOf(), this)
        viewModel.items.observe(viewLifecycleOwner, { newItems ->
            itemAdapter.setData(newItems)
        })

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.adapter = itemAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<FloatingActionButton>(R.id.add_button)?.setOnClickListener {
            addNewItem()
        }
    }

    override fun onItemClick(position: Int) {
        val options = arrayOf("delete")
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Want to delete?")
        builder.setItems(options) { _, optionId ->
            dispatchAction(optionId, viewModel.items.value!![position])
        }
        builder.show()
    }

    fun dispatchAction(optionId: Int, item: Item) {
        when (optionId) {
            0 -> {
                viewModel.deleteItem(item)
            }
        }
    }

    private fun addNewItem() {
        val builder = AlertDialog.Builder(context)
        val form = layoutInflater.inflate(R.layout.add_item_dialogue, null, false)
        builder.setView(form)

        val wishItem: EditText = form.findViewById(R.id.itemName)
        val description: EditText = form.findViewById(R.id.item_description)
        val price: EditText = form.findViewById(R.id.item_price)


        builder.setPositiveButton("Add") { _, _ ->

            val newItem = Item(
                wishItem.text.toString(),
                description.text.toString(),
                price.text.toString(),
            )
            viewModel.addItem(newItem)
            Toast.makeText(context, "Wish item added successfully", Toast.LENGTH_SHORT).show()
        }

        builder.show()
    }


}
