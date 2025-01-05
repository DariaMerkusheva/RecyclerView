package otus.gpb.recyclerview

import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatViewHolder(
    private val view: View,
    private val listener: Listener
) : RecyclerView.ViewHolder(view) {

    private val image: ImageView by lazy { view.findViewById(R.id.imageAvatar) }
    private val name: TextView by lazy { view.findViewById(R.id.textViewName) }
    private val status: TextView by lazy { view.findViewById(R.id.textViewStatus) }
    private val time: TextView by lazy { view.findViewById(R.id.time) }
    private val message: TextView by lazy { view.findViewById(R.id.message) }
    private val imageVoice: ImageView by lazy { view.findViewById(R.id.imageVOIP) }
    private val iconVerif: ImageView by lazy { view.findViewById(R.id.icon_verified) }
    private val iconScam: ImageView by lazy { view.findViewById(R.id.icon_scam) }
    private val iconMute: ImageView by lazy { view.findViewById(R.id.icon_mute) }
    private val counter: TextView by lazy { view.findViewById(R.id.counter) }
    private val iconDelivered: ImageView by lazy { view.findViewById(R.id.delivered) }
    private val root: ViewGroup by lazy { view.findViewById(R.id.root) }
    private val delete: View by lazy { view.findViewById(R.id.delete) }

    fun bind(item: ChatItem) {
        name.text = item.name
        status.text = item.status
        message.text = item.message
        imageVoice.visibility = if (item.voice) VISIBLE else INVISIBLE
        iconVerif.visibility = if (item.verified) VISIBLE else INVISIBLE
        iconScam.visibility = if (item.scam) VISIBLE else INVISIBLE
        iconMute.visibility = if (item.mute) VISIBLE else INVISIBLE
        counter.visibility = if (item.counter > 0) VISIBLE else INVISIBLE
        counter.text = item.counter.toString()
        time.text = item.dateTime
        iconDelivered.visibility = if (item.delivered && !item.read) VISIBLE else INVISIBLE

        val id: Int? =  if (item.read) R.drawable.ic_doublecheck
                        else if (item.delivered && !item.read) R.drawable.ic_check
                        else if (item.delivered && item.read) R.drawable.ic_doublecheck
                        else null

        if (id != null) {
            iconDelivered.setImageResource(id)
            iconDelivered.visibility = VISIBLE
        }
        else{
            iconDelivered.visibility = INVISIBLE
        }

        image.setImageResource(item.imageId)

        root.setOnClickListener { listener.onItemClicked(item.id) }
        delete.setOnClickListener { listener.onItemActionClicked(item.id) }
    }

}