package com.industry.branchinternationalproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MessageThreadsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_threads)

        // Initialize RecyclerView and adapter
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewMessageThreads)
        val adapter = MessageThreadsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Populate the RecyclerView with message threads
        retrieveMessageThreads()

        // Set item click listener to navigate to MessageThreadActivity
        adapter.setOnItemClickListener { messageThread ->
            val intent = Intent(this, MessageThreadActivity::class.java)
            intent.putExtra("messageThreadId", messageThread.id)
            startActivity(intent)
        }
    }

    private fun retrieveMessageThreads() {
        // Implement logic to retrieve message threads from the server and update the adapter
    }
}
