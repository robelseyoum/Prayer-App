package com.robelseyoum3.perseusprayer.data.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.robelseyoum3.perseusprayer.data.model.PrayerMethods


@Dao
interface PrayerMethodsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertAndReplace(prayerMethods: PrayerMethods): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)  //id data is already exist ignore it
    fun insertOnIgnore(prayerMethods: PrayerMethods): Long

   @Query("UPDATE prayer_methods SET methodBased =:methodBased")
    fun updatePrayerMethods(methodBased:  MutableMap<String, String>)

    @Query("SELECT * from prayer_methods")
    fun selectAllPrayerMethod() : PrayerMethods

    @Query("DELETE from prayer_methods")
    fun deleteAllPrayerMethod()

}