package test.test.testgosport.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import test.test.testgosport.model.MealInfo
import test.test.testgosport.repository.MenuRepository

class MenuViewModel: ViewModel() {
    private val repository = MenuRepository()

    private val mealsLiveData = MutableLiveData<List<MealInfo>>()
    private val errorToastLiveData = MutableLiveData<Unit>()

    val meals: LiveData<List<MealInfo>>
        get() = mealsLiveData
    val errorToast: LiveData<Unit>
        get() = errorToastLiveData

    fun getMeals(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mealsLiveData.postValue(repository.getMeals())
                Log.i("GET INFO", "meals list")
            }catch (e:Throwable){
                errorToastLiveData.postValue(Unit)
                Log.e("ERROR", e.stackTraceToString())
            }
        }
    }
}