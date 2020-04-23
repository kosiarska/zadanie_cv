package pl.michal.tretowicz.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_basic_info.view.*
import pl.michal.tretowicz.R
import pl.michal.tretowicz.data.remote.model.BasicInfo
import pl.michal.tretowicz.data.remote.model.CvResponse
import pl.michal.tretowicz.util.extension.load
import pl.michal.tretowicz.util.extension.sendEmail
import pl.michal.tretowicz.util.extension.viewUrl
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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            VIEW_BASIC_INFO -> {
                return ViewHolderBasicInfo(layoutInflater.inflate(R.layout.item_basic_info, parent, false))
            }

            VIEW_WORK -> {
                return ViewHolderWorkInfo(layoutInflater.inflate(R.layout.item_work, parent, false))
            }
        }
        throw IllegalStateException("no such view for $viewType view type")
    }

    override fun getItemCount() = cvData.workInfo.size + 1

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_BASIC_INFO else VIEW_WORK
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(getItemViewType(position)) {
            VIEW_BASIC_INFO -> {
                val h = holder as ViewHolderBasicInfo
                h.bind(cvData.basicInfo)
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

class ViewHolderWorkInfo(itemView: View) : RecyclerView.ViewHolder(itemView)