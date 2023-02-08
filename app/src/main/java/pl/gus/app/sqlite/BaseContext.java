package pl.gus.app.sqlite;


import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import static androidx.room.Room.databaseBuilder;

@Database(version = 1, entities = {User.class})
public abstract class BaseContext extends RoomDatabase {
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
                            .build();
                }
            }
        }
        return  INSTANCE;
    }
}
