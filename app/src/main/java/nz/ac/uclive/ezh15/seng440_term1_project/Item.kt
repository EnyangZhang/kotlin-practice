package nz.ac.uclive.ezh15.seng440_term1_project

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
class Item(
    @ColumnInfo var wishItem: String,
    @ColumnInfo var description: String,
    @ColumnInfo var price: String
) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0

    override fun toString(): String {
        return wishItem
    }
}