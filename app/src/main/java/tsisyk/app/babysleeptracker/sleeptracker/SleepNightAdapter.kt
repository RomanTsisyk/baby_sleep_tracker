package tsisyk.app.babysleeptracker.sleeptracker


import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tsisyk.app.babysleeptracker.R
import tsisyk.app.babysleeptracker.convertDurationToFormatted
import tsisyk.app.babysleeptracker.convertNumericQualityToString
import tsisyk.app.babysleeptracker.database.SleepNight


class SleepNightAdapter : RecyclerView.Adapter<SleepNightAdapter.ViewHolder>() {

    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_sleep_night, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
        private val quality: TextView = itemView.findViewById(R.id.quality_string)
        private val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)

        fun bind(item: SleepNight) {
            var  res = itemView.context.resources
            sleepLength.text =convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
            quality.text = convertNumericQualityToString(item.sleepQuality, res)
            qualityImage.setImageResource(when (item.sleepQuality) {
                    0 -> R.drawable.baby
                    1 -> R.drawable.cry_baby
                    2 -> R.drawable.diaper
                    3 -> R.drawable.feeding_bottle
                    else -> R.drawable.baby
                }
            )
        }
    }
}