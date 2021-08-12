package com.egwusi.demo4savics;


import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;


@Dao
public interface FoodDao {

    @Query("SELECT * FROM Foods")
    List<Food> getFoods();

}
