package com.industry.branchinternationalproject

import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MessageThreadsAdapter : RecyclerView.Adapter<MessageThreadsAdapter.MessageThreadViewHolder>() {
    private val messageThreads: MutableList<MessageThread> = mutableListOf()
    private var onItemClickListener: ((MessageThread) -> Unit)? = null

    inner class MessageThreadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(messageThread: MessageThread) {
            // Bind message thread data to the views
            itemView.setOnClickListener { onItemClickListener?.invoke(messageThread) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageThreadViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message_thread, parent, false)
        return MessageThreadViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageThreadViewHolder, position: Int) {
        val messageThread = messageThreads[position]
        holder.bind(messageThread)
    }

    override fun getItemCount(): Int = messageThreads.size

    fun setOnItemClickListener(listener: (MessageThread) -> Unit) {
        onItemClickListener = listener
    }

    fun setMessageThreads(threads: List<MessageThread>) {
        messageThreads.clear()
        messageThreads.addAll(threads)
        notifyDataSetChanged()
    }

    fun addMessage(message: Message) {


    }


}
