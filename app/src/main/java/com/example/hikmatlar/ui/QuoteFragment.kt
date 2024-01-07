package com.example.hikmatlar.ui

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.recyclerViewAdapter.MVVM.EntityQuote
import com.example.habittracker.recyclerViewAdapter.MVVM.QuoteViewModel
import com.example.hikmatlar.Backend.Api.ApiService.ApiClient
import com.example.hikmatlar.Backend.Api.ApiService.ApiService
import com.example.hikmatlar.Backend.Api.ApiService.Quote
import com.example.hikmatlar.Backend.Api.ApiService.Quotes
import com.example.hikmatlar.R
import com.example.hikmatlar.ui.QuoteRv.QuoteAdapter
import com.example.hikmatlar.databinding.FragmentQuoteBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class QuoteFragment : Fragment() {

    private var _binding : FragmentQuoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var rv : RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter : QuoteAdapter
    private lateinit var apiService: ApiService

    private lateinit var viewModel: QuoteViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(QuoteViewModel::class.java)

        rv = binding.rv
        layoutManager =  LinearLayoutManager(binding.root.context)

        apiService = ApiClient.create()
        val call = apiService.getQuotes()
        val btnListener  = object: OnItemClickListener{
            override fun onSaveBtnClicked(quotes: EntityQuote) {
                viewModel.insertQuote(quotes)
            }
        }
        call.enqueue(object : Callback<Quotes> {
            override fun onResponse(call: Call<Quotes>, response: Response<Quotes>) {
                if (response.isSuccessful) {
                    // Handle successful response
                    val data = response.body()
                    d("data", data.toString())
                    val listData = data!!.quotes
                      adapter = QuoteAdapter(context!!, listData, viewModel, listener = btnListener)
                      rv.layoutManager = layoutManager
                      rv.adapter = adapter
//                    Search
                        binding.editTextSearch.doOnTextChanged { text, start, before, count ->
                            adapter.filter(text.toString())
                            when(adapter.itemCount){
                                0 -> binding.ifEmpty.visibility = View.VISIBLE
                                else ->  binding.ifEmpty.visibility = View.GONE
                            }
                        }
                    // Do something with the data
                } else {
                    d("data", response.message().toString())
                    // Handle error response
                    // You can get the error details from response.errorBody()
                }
            }

            override fun onFailure(call: Call<Quotes>, t: Throwable) {
                d("data", t.toString())
                // Handle network errors or other failures
                loadFragment(SavedFragment())
                (activity as? MainActivity)?.navView?.selectedItemId = R.id.saved
            }
        })


    }
    interface OnItemClickListener{
        fun onSaveBtnClicked(quotes: List<Quote>)
    }

    private fun loadFragment(fragment: Fragment){
        val transaction = activity?.supportFragmentManager?.beginTransaction()!!
        transaction.replace(R.id.contentView, fragment)
        transaction.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
