package nz.ac.uclive.ezh15.seng440_term1_project

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class ItemRepository(private val itemDAO: ItemDAO) {
    val items: Flow<List<Item>> = itemDAO.getAll()
    val numItems: Flow<Int> = itemDAO.getCount()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(item: Item) {
        itemDAO.insert(item)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(item: Item) {
        itemDAO.delete(item)
    }

}