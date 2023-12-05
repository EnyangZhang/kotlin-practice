package nz.ac.uclive.ezh15.seng440_term1_project

import android.animation.ObjectAnimator
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_analyze.*
import java.lang.ref.WeakReference

class AnalyzeFragment : Fragment() {
    private lateinit var progressBar: ProgressBar
    private var itemList: List<Item> = listOf()
    private var sum: Int = 0
    private var currentProgress = 60
    private val viewModel: ItemViewModel by activityViewModels(){
        ItemsViewModelFactory((activity?.application as AsUWishApplication).repository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_analyze, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.items.observe(viewLifecycleOwner, { newItems ->
            itemList = newItems
        })
        for(item: Item in itemList){
            currentProgress += item.price.toInt()
        }

        progressBar =  view.findViewById<ProgressBar>(R.id.progressBar)
        progressBar.max = 100

        ObjectAnimator.ofInt(progressBar, "progress", currentProgress)
            .setDuration(2000)
            .start()
    }


}


