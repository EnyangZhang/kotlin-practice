package nz.ac.uclive.ezh15.seng440_term1_project

import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class ItemViewModel(private val itemRepository: ItemRepository): ViewModel() {
    val items: LiveData<List<Item>> = itemRepository.items.asLiveData()
    val numItems: LiveData<Int> = itemRepository.numItems.asLiveData()

    fun addItem(item: Item) = viewModelScope.launch {
        itemRepository.insert(item)
    }

    fun deleteItem(item:Item) = viewModelScope.launch{
        itemRepository.delete(item)
    }

}

class ItemsViewModelFactory(private val repository: ItemRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


