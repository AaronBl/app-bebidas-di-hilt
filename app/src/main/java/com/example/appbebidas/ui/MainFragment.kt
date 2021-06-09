package com.example.appbebidas.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appbebidas.AppDataBase
import com.example.appbebidas.R
import com.example.appbebidas.data.DataSourceImpl
import com.example.appbebidas.data.model.Drink
import com.example.appbebidas.domain.RepoImpl
import com.example.appbebidas.ui.viewmodel.MainViewModel
import com.example.appbebidas.ui.viewmodel.VMFactory
import com.example.appbebidas.vo.Resource
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment(),MainAdapter.OnTragoClickListener {

    private val viewModel by activityViewModels<MainViewModel> { VMFactory(RepoImpl(DataSourceImpl(
        AppDataBase.getDataBase(requireContext())))) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpSeacrhView()
        setUpObservers()
        btn_ir_favoritos.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_favoritosFragment)
        }



    }
private fun setUpObservers(){
        viewModel.fetchTragosList.observe(viewLifecycleOwner, Observer {result ->
            when (result) {
                is Resource.loading ->{
                    progress_bar.visibility = View.VISIBLE
                }
                is Resource.Success ->{
                    progress_bar.visibility = View.GONE
                    rv_tragos.adapter = MainAdapter(requireContext(),result.data.toMutableList(),this)
                }
                is Resource.Failure ->{
                    progress_bar.visibility = View.GONE
                    Toast.makeText(requireContext(),"Ocurrio un error al traer los datos ${result.exception}",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    private fun setUpRecyclerView (){
        rv_tragos.layoutManager = LinearLayoutManager(requireContext())
        rv_tragos.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }

    private fun setUpSeacrhView(){
        search_view.setOnQueryTextListener(object :SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setTrago(query!!)
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }

    override fun onTragoClick(drink: Drink,position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("drink",drink)
     findNavController().navigate(R.id.action_mainFragment_to_tragosDetalleFragment,bundle)

    }

}
