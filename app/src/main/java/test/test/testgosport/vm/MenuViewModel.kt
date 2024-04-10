package test.test.testgosport.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import test.test.testgosport.model.CategoryInfo
import test.test.testgosport.model.MealInfo
import test.test.testgosport.repository.MenuRepository

class MenuViewModel: ViewModel() {
    private val repository = MenuRepository()

    private val mealsLiveData = MutableLiveData<List<MealInfo>>()
    private val errorToastLiveData = MutableLiveData<Unit>()
    private val sortedMealsLiveData = MutableLiveData<List<MealInfo>>()
    private val categoriesLiveData = MutableLiveData<List<CategoryInfo>>()
    private val newsLiveData = MutableLiveData<List<Int>>()

    val meals: LiveData<List<MealInfo>>
        get() = mealsLiveData
    val errorToast: LiveData<Unit>
        get() = errorToastLiveData
    val sortedMeals: LiveData<List<MealInfo>>
        get() = sortedMealsLiveData
    val categories: LiveData<List<CategoryInfo>>
        get() = categoriesLiveData
    val news:LiveData<List<Int>>
        get() = newsLiveData


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

    fun getSortedMeals(category:String){
        val mealList = mutableListOf<MealInfo>()
        meals.value?.forEach {
            if (it.strCategory==category){
                mealList.add(it)
            }
        }
        sortedMealsLiveData.postValue(mealList)
    }

    fun getCategories(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                categoriesLiveData.postValue(repository.getCategories())
            }catch (e:Throwable){
                errorToastLiveData.postValue(Unit)
                Log.e("ERROR", e.stackTraceToString())
            }
        }
    }

    fun getNews(){
        viewModelScope.launch {
            try {
                newsLiveData.postValue(repository.getNews())
            }catch (e:Throwable){
                errorToastLiveData.postValue(Unit)
            }
        }
    }
}