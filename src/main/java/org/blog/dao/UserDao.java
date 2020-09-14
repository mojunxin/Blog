package org.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.blog.model.User;

import java.util.List;

public interface UserDao {
    Integer dataCount();

    List<User> getListUser(@Param("x")Integer x,@Param("y")Integer y);

    User getByAccount(@Param("account") String account);

    User getById(@Param("id") String id);

    User getByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    void delete(String[] ids);

    void update(User user);

    void save(User user);
}
