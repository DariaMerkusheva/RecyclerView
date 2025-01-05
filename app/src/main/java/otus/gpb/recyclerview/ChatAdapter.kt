package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<ChatViewHolder>() {

    private var list = listOf<ChatItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.message, parent, false)

        return ChatViewHolder(view, listener)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val item = list.getOrNull(position)
        item?.let {
            holder.bind(item)

        }
    }

    fun setData(newList: List<ChatItem>) {
        list = newList
    }

    fun removeItem(id: Int) {

        list = list.toMutableList().also {
            it.removeAt(id)
        }.toList()

        notifyItemRangeRemoved(id, 1)
    }
}