package com.industry.branchinternationalproject

//implementing the logic to retrieve message threads
//from the API and populate the RecyclerView with message threads
// in the ConversationActivity's onCreate method
data class MessageThread(
    val id: Int,
    val user_id: Int,
    val agent_id: Int?,
    val body: String,
    val timestamp: String
)

