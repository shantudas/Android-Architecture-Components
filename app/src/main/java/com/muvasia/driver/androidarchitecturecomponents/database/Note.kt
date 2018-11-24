package com.muvasia.driver.androidarchitecturecomponents.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


/*class Note(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "body")
    val body: String
)*/
@Entity(tableName = "note_table")
class Note(title: String, body: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "title")
    var title: String? = title

    @ColumnInfo(name = "body")
    var body: String? = body

}

