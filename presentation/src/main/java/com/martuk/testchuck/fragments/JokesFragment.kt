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
import com.kirmartyukl.domain.dto.ChuckNorrisJokeDTO
import com.kirmartyukl.domain.repository.LoadResult
import com.kirmartyukl.domain.usecase.GetJokesUseCase
import com.kirmartyukl.domain.usecase.GetJokesUseCaseImpl
import com.kirmartyukl.domain.usecase.RxGetJokesUseCase
import com.martuk.testchuck.adapters.ChuckNorrisJokeRecyclerViewAdapter
import com.martuk.testchuck.R
import com.martuk.testchuck.gone
import com.martuk.testchuck.viewmodels.JokesViewModel
import com.martuk.testchuck.visible
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.custom_fab_widget.view.*
import kotlinx.android.synthetic.main.fragment_jokes.*
import javax.inject.Inject

@AndroidEntryPoint
class JokesFragment : Fragment() {

    private val mJokesViewModel by viewModels<JokesViewModel>()
    private val chuckNorrisJokeRecyclerViewAdapter = ChuckNorrisJokeRecyclerViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_jokes.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = chuckNorrisJokeRecyclerViewAdapter
        }

        mJokesViewModel.jokes.observe(viewLifecycleOwner, {
            chuckNorrisJokeRecyclerViewAdapter.jokes = it
        })

        mJokesViewModel.progress.observe(viewLifecycleOwner, {
            when (it) {
                is LoadResult.Loading ->
                    pgb_container.visible()
                is LoadResult.Success ->
                    pgb_container.gone()
                is LoadResult.Error -> {
                    pgb_container.gone()
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

    companion object {
        fun newInstance(): JokesFragment {
            return JokesFragment()
        }
    }
}