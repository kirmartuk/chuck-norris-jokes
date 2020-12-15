package com.martuk.testchuck.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.martuk.testchuck.adapters.ChuckNorrisJokeRecyclerViewAdapter
import com.martuk.testchuck.R
import com.martuk.testchuck.api.LoadResult
import com.martuk.testchuck.viewmodels.JokesViewModel
import kotlinx.android.synthetic.main.custom_fab_widget.view.*
import kotlinx.android.synthetic.main.fragment_jokes.*


class JokesFragment : Fragment() {

    private val mJokesViewModel: JokesViewModel by viewModels()

    companion object {

        fun newInstance(): JokesFragment {
            return JokesFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mJokesViewModel.jokes.observe(viewLifecycleOwner, {
            rv_jokes.apply {
                layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                adapter = ChuckNorrisJokeRecyclerViewAdapter(it)
            }

        })
        mJokesViewModel.progress.observe(viewLifecycleOwner, {
            when (it) {
                is LoadResult.Loading ->
                    pgb_container.visibility = View.VISIBLE
                is LoadResult.Success ->
                    pgb_container.visibility = View.GONE
                is LoadResult.Error -> {
                    pgb_container.visibility = View.GONE
                    Snackbar.make(view, getString(R.string.error), Snackbar.LENGTH_SHORT).show()
                }
            }
        })

        with(fab) {
            ibtn_load.setOnClickListener {
                text_layout.error = null
                try {
                    if (et_count_of_joke.text.toString().isEmpty()) {
                        text_layout.error = context.getString(R.string.not_empty)
                    } else {
                        mJokesViewModel.loadChuckNorrisJokes(
                            et_count_of_joke.text.toString().toInt()
                        )
                    }
                } catch (exception: NumberFormatException) {
                    Snackbar.make(
                        view,
                        context.getString(R.string.number_is_too_big),
                        Snackbar.LENGTH_SHORT
                    ).show()
                    Log.e("numberformatexception", exception.toString())
                }

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_jokes, container, false)
    }
}