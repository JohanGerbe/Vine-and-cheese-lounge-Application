package fr.johan.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.johan.app.MainActivity
import fr.johan.app.R
import fr.johan.app.VinRepository.Singleton.vinList
import fr.johan.app.adapter.VinAdapter
import fr.johan.app.adapter.VinItemDecoration

class HomeFragment (
    private val context: MainActivity
):    Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)


        // recuperer le recyclerview
        val horizontaleRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontaleRecyclerView.adapter = VinAdapter (context, vinList,R.layout.item_horizontale_vin)

        // recuperer le second recyclerview
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = VinAdapter(context, vinList, R.layout.item_vertical_vin)
        verticalRecyclerView.addItemDecoration(VinItemDecoration())

        return view
    }

}






