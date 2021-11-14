package com.martuk.testchuck.adapters

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kirmartyukl.domain.dto.ChuckNorrisJoke
import com.martuk.testchuck.R

class ChuckNorrisJokeRecyclerViewAdapter :
    RecyclerView.Adapter<ChuckNorrisJokeRecyclerViewAdapter.ViewHolder>() {

    var jokes: ArrayList<ChuckNorrisJoke> = arrayListOf()

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.joke_item, parent, false)) {

        private var mJokeTextView: TextView? = null

        init {
            mJokeTextView = itemView.findViewById(R.id.tv_joke)
        }

        fun bind(chuckNorrisJoke: ChuckNorrisJoke) {
            mJokeTextView?.run {
                // Should use Html.fromHtml(), because jokes contains special signs
                text = "${Html.fromHtml(chuckNorrisJoke.joke)}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentJoke = jokes[position]
        holder.bind(currentJoke)
    }

    override fun getItemCount(): Int = jokes.size


}