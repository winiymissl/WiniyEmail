package com.example.winiychat.model.bean

data class InBoxRecyclerviewBean(
    val name: String,
    val isRead: Boolean,
    val content: String,
    val subject: String,
    val time: String,
    val avatar: String,
    val isCollected: Boolean
)
