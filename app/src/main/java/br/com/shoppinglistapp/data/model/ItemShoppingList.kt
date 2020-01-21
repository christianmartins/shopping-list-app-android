package br.com.shoppinglistapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemShoppingList(
    @PrimaryKey var id: String,
    @ColumnInfo var description: String,
    @ColumnInfo var selected: Boolean,
    @ColumnInfo var shoppingListId: String,
    @ColumnInfo var deleted: Boolean,
    @ColumnInfo var createAt: String,
    @ColumnInfo var updateAt: String,
    @ColumnInfo var sent: Boolean
) : BaseModel()