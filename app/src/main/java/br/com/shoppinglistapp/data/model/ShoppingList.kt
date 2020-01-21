package br.com.shoppinglistapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id", "userId"], indices = [Index(value = ["id", "userId"])])
data class ShoppingList (
    @SerializedName("id") @ColumnInfo var id: String,
    @SerializedName("title") @ColumnInfo var title: String,
    @SerializedName("description") @ColumnInfo var description: String,
    @SerializedName("userId") @ColumnInfo var userId: String,
    @SerializedName("authorName") @ColumnInfo var authorName: String,
    @SerializedName("createAt") @ColumnInfo var createAt: Long,
    @SerializedName("updateAt") @ColumnInfo var updateAt: Long,
    @SerializedName("deleted") @ColumnInfo var deleted: Boolean,
    @SerializedName("totalItemsToComplete") @ColumnInfo var totalItemsToComplete: Int,
    @SerializedName("currentItemsToComplete") @ColumnInfo var currentItemsToComplete: Int,
    @Expose(deserialize = false) @ColumnInfo var sent: Boolean
): BaseModel()