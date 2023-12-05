package nz.ac.uclive.ezh15.seng440_term1_project

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDAO {

    @Insert
    suspend fun insert(item: Item): Long

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * FROM item")
    fun getAll(): Flow<List<Item>>

    @Query("SELECT COUNT(*) FROM item")
    fun getCount(): Flow<Int>

}