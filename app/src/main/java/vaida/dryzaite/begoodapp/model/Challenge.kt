package vaida.dryzaite.begoodapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenge")
data class Challenge (
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name= "url") var url: String = "",
    @ColumnInfo(name = "completed") var isCompleted: Boolean = false,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int? = null
){
    val isActive
        get() = !isCompleted
}


