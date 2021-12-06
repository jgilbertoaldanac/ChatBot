package aldana.gilberto.chatbot.ui

import aldana.gilberto.chatbot.R
import aldana.gilberto.chatbot.data.Message
import aldana.gilberto.chatbot.utils.Constans.RECEIVE_ID
import aldana.gilberto.chatbot.utils.Constans.SEND_ID
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.message_item.view.*

class MessangingAdapter: RecyclerView.Adapter<MessangingAdapter.MessageViewHolder>() {
    var messageList = mutableListOf<Message>()
    inner class MessageViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener{
                messageList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false))
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {

        val currentMessage = messageList[position]
        when(currentMessage.id){
            SEND_ID->{
                holder.itemView.tv_message.apply{
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.tv_bot_message.visibility = View.GONE
            }
            RECEIVE_ID->{
                holder.itemView.tv_bot_message.apply{
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.tv_message.visibility = View.GONE
            }
        }

    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    fun insertMessage(message: Message){
        this.messageList.add(message)
        notifyItemInserted(messageList.size)
        notifyDataSetChanged()
    }

}