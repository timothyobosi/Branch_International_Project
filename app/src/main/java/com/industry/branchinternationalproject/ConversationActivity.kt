package com.industry.branchinternationalproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.industry.branchinternationalproject.RetrofitClient.apiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ConversationActivity : AppCompatActivity() {

    private lateinit var editTextMessage: EditText
    private lateinit var buttonSend: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MessageThreadsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation)

        editTextMessage = findViewById(R.id.editTextMessage)
        buttonSend = findViewById(R.id.buttonSend)
        recyclerView = findViewById(R.id.recyclerViewMessages)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MessageThreadsAdapter() // Use  MessageThreadsAdapter
        recyclerView.adapter = adapter

        buttonSend.setOnClickListener {
            val message = editTextMessage.text.toString().trim()
            if (message.isNotEmpty()) {
                // Call a function to send the message to the API
                sendMessageToAPI(adapter, message)
            }
        }

        // Call function to retrieve message threads
        retrieveMessageThreads()
    }


    private fun retrieveMessageThreads() {
        apiService.getAllMessageThreads().enqueue(object : Callback<List<MessageThread>> {
            override fun onResponse(call: Call<List<MessageThread>>, response: Response<List<MessageThread>>) {
                if (response.isSuccessful) {
                    val messageThreads = response.body()
                    if (messageThreads != null) {
                        adapter.setMessageThreads(messageThreads)
                    }
                } else {
                    showToast("Failed to retrieve message threads")
                }
            }

            override fun onFailure(call: Call<List<MessageThread>>, t: Throwable) {
                showToast("Network error occurred")
            }
        })
    }

    private fun sendMessageToAPI(adapter: MessageThreadsAdapter, message: String) {
        // Implement the logic to send the message to the API
        // This can include making a network request to the appropriate API endpoint
        // and handling the response appropriately

        // For example, you can use Retrofit to make a network request
        val requestBody = mapOf("body" to message)
        apiService.createMessage(requestBody).enqueue(object : Callback<MessageResponse> {
            override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>) {
                if (response.isSuccessful) {
                    // Message sent successfully
                    val sentMessage = response.body()?.message
                    // Update the UI to display the sent message
                    if (sentMessage != null) {
                        adapter.addMessage(sentMessage)
                        editTextMessage.text.clear() // Clear the message input field
                    }
                } else {
                    // Handle unsuccessful response
                    showToast("Failed to send message")
                }
            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                // Handle network errors
                showToast("Network error occurred")
            }
        })
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
