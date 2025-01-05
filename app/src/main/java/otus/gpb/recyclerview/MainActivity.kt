package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), Listener {

    private val adapter: ChatAdapter by lazy { ChatAdapter(this) }
    private var list: List<ChatItem> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter

        list = generateList()
        adapter.setData(list)

        ItemTouchHelper(ItemTouchHelperCallBack()).attachToRecyclerView(recyclerView)


    }

    private fun generateList() = run {
        val list = mutableListOf<ChatItem>()
        repeat(10) {
            val chatItem = ChatItem(
                id = it,
                name = "Ivan $it",
                status = "stat",
                message = "This is message",
                dateTime = "Fri",
                mute = (it % 2 == 1),
                counter = it,
                verified = (it % 2 == 1),
                voice = (it % 2 == 0),
                scam = (it % 4 == 0),
                delivered = true,
                read = (it % 2 == 1),
                imageId = R.drawable.avatar,
            )
            list.add(chatItem)
        }
        list.toList()
    }

    override fun onItemClicked(id: Int) {
        Toast.makeText(this, "onItemClicked $id", Toast.LENGTH_SHORT).show()
    }

    override fun onItemActionClicked(id: Int) {
        adapter.removeItem(id)
    }
}