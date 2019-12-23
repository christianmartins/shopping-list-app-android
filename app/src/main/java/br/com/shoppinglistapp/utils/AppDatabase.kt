package br.com.shoppinglistapp.utils

import android.content.Context
import android.util.Log
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.shoppinglistapp.data.dao.ItemShoppingListDao
import br.com.shoppinglistapp.data.dao.ShoppingListDao
import br.com.shoppinglistapp.data.model.ShoppingList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [(ShoppingList::class)], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getItemShoppingListDao(): ItemShoppingListDao
    abstract fun getShoppingListDao(): ShoppingListDao

    companion object {
        private var sInstance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                sInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "shoppinglist.db"
                ).addCallback(AppDatabaseCallback()).fallbackToDestructiveMigration().build()
            }
            return sInstance!!
        }

        private class AppDatabaseCallback : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                sInstance?.let {
                    println("AppDatabase: OpenDB!")
                }
            }
        }
    }


}