package pl.michal.tretowicz.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.michal.tretowicz.R
import pl.michal.tretowicz.data.model.CvData
import java.lang.IllegalStateException

/**
 * Created by Michał Trętowicz
 */
class CvAdapter(private val context: Context, private val cvData: CvData) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    companion object {
        const val VIEW_BASIC_INFO = 0
        const val VIEW_WORK = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
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
        return if(position == 0) VIEW_BASIC_INFO else VIEW_WORK
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

}

class ViewHolderBasicInfo(itemView : View) : RecyclerView.ViewHolder(itemView)
class ViewHolderWorkInfo(itemView : View) : RecyclerView.ViewHolder(itemView)