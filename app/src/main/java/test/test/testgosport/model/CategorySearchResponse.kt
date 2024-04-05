package test.test.testgosport.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CategorySearchResponse(
    @SerializedName("categories") val categories: List<CategoryInfo>
)