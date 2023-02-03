package com.example.projecteyebrow.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projecteyebrow.database.tables.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table ORDER BY ShopName ASC")
    fun readAllShopData(): LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShopData(selectedShop: UserEntity)

    @Delete
    suspend fun deleteShopData(selectedShop: UserEntity)
}