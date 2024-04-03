package test.test.testgosport.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class MealSearchResponse(
    @SerializedName("meals") val meals: List<MealInfo>
)
