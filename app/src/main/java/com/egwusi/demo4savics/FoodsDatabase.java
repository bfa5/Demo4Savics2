package com.egwusi.demo4savics;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

import static com.egwusi.demo4savics.Constants.DATA_BASE_NAME;


@Database(entities = {Food.class}, version = 1)
public abstract class FoodsDatabase extends RoomDatabase {

    private static FoodsDatabase instance;
    public abstract FoodDao foodDao();
    private LiveData<List<Food>> foodList;
    private static final Object sLock = new Object();

    public static FoodsDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        FoodsDatabase.class, DATA_BASE_NAME)
                        .build();
                instance.init();
            }
            return instance;
        }
    }
    private void init() {

    }

    public LiveData<List<Food>> getFoods() {
        return foodList;
    }

}
