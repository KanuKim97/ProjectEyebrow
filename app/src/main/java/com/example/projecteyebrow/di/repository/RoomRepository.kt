package com.example.projecteyebrow.di.repository

import androidx.lifecycle.LiveData
import com.example.projecteyebrow.database.dao.UserDao
import com.example.projecteyebrow.database.tables.UserEntity
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val userDao: UserDao
) {
    val readAllShopData: LiveData<List<UserEntity>> = userDao.readAllShopData()
    suspend fun insertShopData(shopEntity: UserEntity) = userDao.addShopData(shopEntity)
    suspend fun deleteShopData(shopEntity: UserEntity) = userDao.deleteShopData(shopEntity)
}