package com.example.appbebidas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appbebidas.AppDataBase
import com.example.appbebidas.R
import com.example.appbebidas.data.DataSourceImpl
import com.example.appbebidas.data.model.Drink
import com.example.appbebidas.data.model.DrinkEntity
import com.example.appbebidas.domain.RepoImpl
import com.example.appbebidas.ui.viewmodel.MainViewModel
import com.example.appbebidas.ui.viewmodel.VMFactory
import com.example.appbebidas.vo.Resource
import kotlinx.android.synthetic.main.fragment_favoritos.*

class FavoritosFragment : Fragment(),MainAdapter.OnTragoClickListener {
    private lateinit var adapter: MainAdapter
    private val viewModel by activityViewModels<MainViewModel> { VMFactory(
        RepoImpl(
            DataSourceImpl(
        AppDataBase.getDataBase(requireContext()))
        )
    ) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoritos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpObservers()

    }
    private fun setUpObservers(){
        viewModel.getTragosFavoritos().observe(viewLifecycleOwner, Observer {
            when(it ){
                is Resource.loading ->{}
                is Resource.Success ->{
                 val lista = it.data.map {
                        Drink(it.tragoId,it.image,it.name,it.description)
                  }
                    //val lista = it.data.asDrinkList()
                    adapter = MainAdapter(requireContext(),lista.toMutableList(),this)
                    rv_tragos_favoritos.adapter = adapter
                }
                is Resource.Failure ->{}
            }
        })
    }
    private fun setUpRecyclerView(){
        rv_tragos_favoritos.layoutManager = LinearLayoutManager(requireContext())
        rv_tragos_favoritos.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }

    override fun onTragoClick(drink: Drink,position: Int) {
        viewModel.deleteDrink(DrinkEntity(drink.cocktailId,drink.image,drink.name,drink.description))
        adapter.deleteDrink(position)
    }


}