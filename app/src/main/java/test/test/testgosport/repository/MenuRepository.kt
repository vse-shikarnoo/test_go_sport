package test.test.testgosport.repository

import test.test.testgosport.model.CategoryInfo
import test.test.testgosport.model.MealInfo
import test.test.testgosport.model.MealSearchResponse
import test.test.testgosport.remote.NetworkService

class MenuRepository {

    private val service = NetworkService.create()

    suspend fun getMeals(): List<MealInfo> {
        return service.getMeals().meals
    }

    suspend fun getCategories():List<CategoryInfo>{
        return service.getCategories().categories
    }
}