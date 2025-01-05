package otus.gpb.recyclerview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


class ItemTouchHelperCallBack() : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.LEFT)
            viewHolder.chatAdapter.removeItem(viewHolder.absoluteAdapterPosition)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val itemView = viewHolder.itemView
            var blue = Paint().apply { setColor(Color.parseColor("#3D95D4")) }
            var white = Paint().apply { setColor(Color.parseColor("#FFFFFF")) }

            val icon = ContextCompat.getDrawable(recyclerView.context, R.drawable.ic_archive)?.toBitmap()

            if (dX <= 0) {

                /* swipe left */
                try {
                    c.drawRect(
                        itemView.right.toFloat() + dX, itemView.top.toFloat(),
                        itemView.right.toFloat(), itemView.bottom.toFloat(), blue
                    )

                    if (dX <= -200) {
                        if (icon != null) {
                            c.drawBitmap(
                                icon,
                                itemView.right.toFloat() - icon.width - icon.width / 2,
                                itemView.top.toFloat() + (itemView.bottom.toFloat() - itemView.top.toFloat() - icon.height) / 2,
                                white
                            )
                        }
                    }
                }
                catch (e: Exception) {

                }
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private val RecyclerView.ViewHolder.chatAdapter: ChatAdapter
        get() = bindingAdapter as? ChatAdapter ?: error("Not ChatAdapter")
}