package pl.michal.tretowicz.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.ajalt.timberkt.e
import kotlinx.android.synthetic.main.item_basic_info.view.*
import kotlinx.android.synthetic.main.item_basic_info.view.address
import kotlinx.android.synthetic.main.item_basic_info.view.age
import kotlinx.android.synthetic.main.item_basic_info.view.email
import kotlinx.android.synthetic.main.item_basic_info.view.image
import kotlinx.android.synthetic.main.item_basic_info.view.linkedin
import kotlinx.android.synthetic.main.item_basic_info.view.name
import kotlinx.android.synthetic.main.item_basic_info.view.phoneNumber
import kotlinx.android.synthetic.main.item_work.view.*
import pl.michal.tretowicz.R
import pl.michal.tretowicz.data.remote.model.BasicInfo
import pl.michal.tretowicz.data.remote.model.CvResponse
import pl.michal.tretowicz.data.remote.model.WorkInfoItem
import pl.michal.tretowicz.util.extension.*
import pl.michal.tretowicz.util.picasso.CircleTransform
import java.lang.IllegalStateException
import java.net.URLDecoder

/**
 * Created by Michał Trętowicz
 */
class CvAdapter(private val context: Context, private val cvData: CvResponse) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    companion object {
        const val VIEW_BASIC_INFO = 0
        const val VIEW_WORK = 1
        const val VIEW_HEADER = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            VIEW_BASIC_INFO -> {
                return ViewHolderBasicInfo(layoutInflater.inflate(R.layout.item_basic_info, parent, false))
            }

            VIEW_HEADER -> {
                return ViewHolderHeader(layoutInflater.inflate(R.layout.item_header, parent, false))
            }

            VIEW_WORK -> {
                return ViewHolderWorkInfo(layoutInflater.inflate(R.layout.item_work, parent, false))
            }
        }
        throw IllegalStateException("no such view for $viewType view type")
    }

    override fun getItemCount() = cvData.workInfo.size + 2

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return VIEW_BASIC_INFO
        } else if (position == 1) {
            return VIEW_HEADER
        }
        return VIEW_WORK
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            VIEW_BASIC_INFO -> {
                val h = holder as ViewHolderBasicInfo
                h.bind(cvData.basicInfo)
            }

            VIEW_WORK -> {
                val h = holder as ViewHolderWorkInfo
                h.bind(cvData.workInfo[position - 2])
            }
        }
    }

}

class ViewHolderBasicInfo(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(basicInfo: BasicInfo) {
        itemView.name.text = basicInfo.name
        itemView.image.load(basicInfo.photo) { it.fit().transform(CircleTransform()).centerInside() }
        itemView.email.text = itemView.context.getString(R.string.email, basicInfo.email)
        itemView.linkedin.text = itemView.context.getString(R.string.linkedin, URLDecoder.decode(basicInfo.linkedin, "UTF-8"))
        itemView.age.text = itemView.context.getString(R.string.age, basicInfo.age.toString())
        itemView.phoneNumber.text = itemView.context.getString(R.string.phoneNumber, basicInfo.phoneNumber)
        itemView.address.text = itemView.context.getString(R.string.address, basicInfo.address)

        itemView.linkedin.setOnClickListener {
            basicInfo.linkedin.viewUrl(itemView.context)
        }

        itemView.email.setOnClickListener {
            basicInfo.email.sendEmail(itemView.context)
        }
    }
}

class ViewHolderWorkInfo(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(workInfoItem: WorkInfoItem) {
        if (workInfoItem.companyLogo.isEmpty()) {
            itemView.image.gone()
            itemView.space.gone()
        } else {
            itemView.image.visible()
            itemView.space.visible()
            itemView.image.load(workInfoItem.companyLogo) { it.fit().centerInside() }
        }

        itemView.name.text = workInfoItem.companyName
        itemView.city.text = itemView.context.getString(R.string.city, workInfoItem.place)
        itemView.time.text = itemView.context.getString(R.string.time, workInfoItem.time)
        itemView.description.text = workInfoItem.description

        itemView.setOnClickListener {
            if (workInfoItem.url != null && workInfoItem.url.isNotEmpty()) {
                workInfoItem.url.viewUrl(itemView.context)
            }
        }
        if(workInfoItem.links.isEmpty()) {
            itemView.apps.gone()
        } else {
            itemView.apps.visible()
        }
        itemView.links.adapter = LinksAdapter(itemView.context, workInfoItem.links)
    }
}

class ViewHolderHeader(itemView: View) : RecyclerView.ViewHolder(itemView)