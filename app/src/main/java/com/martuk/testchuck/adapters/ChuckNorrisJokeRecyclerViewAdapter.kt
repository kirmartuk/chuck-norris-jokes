package com.martuk.testchuck.adapters

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.martuk.testchuck.R
import com.martuk.testchuck.entities.ChuckNorrisJoke
import java.net.URLDecoder
import java.nio.charset.CharsetDecoder

class ChuckNorrisJokeRecyclerViewAdapter(private val jokes: ArrayList<ChuckNorrisJoke>) :
    RecyclerView.Adapter<ChuckNorrisJokeRecyclerViewAdapter.ViewHolder>() {
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