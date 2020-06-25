package com.example.mvvmprac.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    var name: String,
    var number: String,
    var initial: String

)