package com.binary.mobile.push

import kotlinx.coroutines.flow.Flow

data class PushMessage(
    val title: String,
    val body: String,
    val data: Map<String, String> = emptyMap(),
    val imageUrl: String? = null,
)

interface PushNotificationService {
    val incomingMessages: Flow<PushMessage>
    suspend fun getToken(): String?
    suspend fun deleteToken()
    suspend fun subscribeToTopic(topic: String)
    suspend fun unsubscribeFromTopic(topic: String)
    fun onTokenRefresh(block: (String) -> Unit)
}
