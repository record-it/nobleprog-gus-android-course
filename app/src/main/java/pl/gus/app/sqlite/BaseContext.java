package pl.gus.app.sqlite;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import static androidx.room.Room.databaseBuilder;

@Database(version = 1, entities = {User.class})
public abstract class BaseContext extends RoomDatabase{
    public abstract UserDao userDao();

    private static BaseContext INSTANCE;

    public static BaseContext getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (BaseContext.class) {
                if (INSTANCE == null) {
                    INSTANCE = databaseBuilder(
                            context.getApplicationContext(),
                            BaseContext.class,
                            "gus_app"
                    )
                            .fallbackToDestructiveMigration()
                            .addCallback(sCallback)
                            .build();
                }
            }
        }
        return  INSTANCE;
    }

    private static RoomDatabase.Callback sCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    User user = new User();
                    user.setFirstName("Adam");
                    user.setLastName("Karolak");
                    user.setBirth("2000-10-20");
                    INSTANCE.userDao().save(user);
                    return null;
                }
            };
            task.execute(null, null);
        }
    };

}
