package pl.michal.tretowicz.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_link.view.*
import pl.michal.tretowicz.R
import pl.michal.tretowicz.util.extension.viewUrl


class LinksAdapter(private val context: Context, private val links: List<String>) : RecyclerView.Adapter<LinkViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
        return LinkViewHolder(layoutInflater.inflate(R.layout.item_link, parent, false))
    }

    override fun getItemCount() = links.size

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
        holder.bind(links[position])
    }
}


class LinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(link: String) {
        itemView.link.text = link
        itemView.link.setOnClickListener {
            link.viewUrl(itemView.context)
        }
    }
}