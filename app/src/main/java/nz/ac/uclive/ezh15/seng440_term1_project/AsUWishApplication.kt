package nz.ac.uclive.ezh15.seng440_term1_project

import android.app.Application

class AsUWishApplication : Application() {
    val database by lazy { ItemDatabase.getDatabase(this) }
    val repository by lazy { ItemRepository(database.itemDAO()) }
}