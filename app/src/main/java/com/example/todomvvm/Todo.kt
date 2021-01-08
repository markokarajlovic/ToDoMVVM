package com.example.todomvvm

import com.google.firebase.firestore.DocumentId
import java.io.Serializable

data class Todo(
    @DocumentId
    var id: String = "",
    var text: String = "",
    var isDone: Boolean = false
)
