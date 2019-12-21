package br.com.shoppinglistapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["id", "userId"], indices = [Index(value = ["id", "userId"])])
data class ShoppingList (
    @PrimaryKey var id: String,
    @ColumnInfo var title: String,
    @ColumnInfo var description: String,
    @PrimaryKey var userId: String,
    @ColumnInfo var authorName: String,
    @ColumnInfo var createAt: Long,
    @ColumnInfo var updateAt: Long,
    @ColumnInfo var deleted: Boolean,
    @ColumnInfo var totalItemsToComplete: Int,
    @ColumnInfo var currentItemsToComplete: Int
): BaseModel()