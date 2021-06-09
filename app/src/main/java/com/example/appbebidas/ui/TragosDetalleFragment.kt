package com.example.appbebidas.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.appbebidas.AppDataBase

import com.example.appbebidas.R
import com.example.appbebidas.data.DataSourceImpl
import com.example.appbebidas.data.model.Drink
import com.example.appbebidas.data.model.DrinkEntity
import com.example.appbebidas.domain.RepoImpl
import com.example.appbebidas.ui.viewmodel.MainViewModel
import com.example.appbebidas.ui.viewmodel.VMFactory
import kotlinx.android.synthetic.main.fragment_tragos_detalle.*

class TragosDetalleFragment : Fragment() {

    private lateinit var drink: Drink
    private val viewModel by activityViewModels<MainViewModel> {
        VMFactory(
			RepoImpl(
				DataSourceImpl(
					AppDataBase.getDataBase(requireContext())
				)
			)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().let {
            drink = it.getParcelable("drink")!!
            Log.d("Detalles", "${drink.toString()}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tragos_detalle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(drink.image).centerCrop().into(img_cocktail)
        cocktail_title.text = drink.name
        cocktail_desc.text = drink.description
        btn_save_or_delete_cocktail.setOnClickListener {
            viewModel.guardarTrago(
                DrinkEntity(
                    drink.cocktailId,
                    drink.image,
                    drink.name,
                    drink.description
                )
            )
            Toast.makeText(requireContext(), "Se gurado el trago a favoritos", Toast.LENGTH_SHORT)
                .show()
        }
    }


}
