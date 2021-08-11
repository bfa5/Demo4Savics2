package com.egwusi.demo4savics;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class MainViewModel extends AndroidViewModel
{

    private Database appDatabase;
    private final LiveData<List<User>> userList;

    public MainViewModel(@NonNull Application application)
    {
        super(application);
        appDatabase = Database.getDatabase(this.getApplication());
        //Initialize User List
        userList = appDatabase.userDao().getAllUsers();
    }

    //Add New User
    public void addUser(User user)
    {
        new addAsyncTask(appDatabase).execute(user);
    }

    // Get Users
    public LiveData<List<User>> getUserList()
    {
        return userList;
    }

    private static class addAsyncTask extends AsyncTask<User, Void, Void> {
        private Database db;

        addAsyncTask(Database appDatabase)
        {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final User... params)
        {
            db.userDao().addUser(params[0]);
            return null;
        }

    }
}
