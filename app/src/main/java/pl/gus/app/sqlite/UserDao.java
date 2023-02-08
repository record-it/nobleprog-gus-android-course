package pl.gus.app.sqlite;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void save(User user);

    @Delete
    void delete(User user);

    @Query("select * from users")
    List<User> findAll();

    @Query("select * from users where id = :id")
    User findById(int id);
}
