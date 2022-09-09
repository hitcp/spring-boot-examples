package org.microframework.webflux.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.microframework.webflux.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shaoyu Liu --- website: <a href="http://hitcp.cn">Hi TCP</a>
 * @date 2022-09-09
 */
//@Mapper
@Repository
//@Component
public class UserDao {
    // FIXME 平常情况不要用Long作为map的key，有坑
    private static final Map<Integer, User> USER_MAP = new HashMap<>();

    static {
        USER_MAP.put(1, new User(1, "张三1"));
        USER_MAP.put(2, new User(2, "张三2"));
        USER_MAP.put(3, new User(3, "张三3"));
        USER_MAP.put(4, new User(4, "张三4"));
        USER_MAP.put(5, new User(5, "张三5"));
    }

    //    @Select("select 1")
    public User select(Integer id) {
        return USER_MAP.get(id);
    }

    public List<User> list() {
        List<User> users = new ArrayList<>();
        for (Map.Entry<Integer, User> user : USER_MAP.entrySet()) {
            users.add(user.getValue());
        }
        return users;
    }


    public boolean insert(User user) {
        USER_MAP.put(user.getId(), user);
        return true;
    }


    public boolean delete(Integer id) {
        USER_MAP.remove(id);
        return true;
    }


    public boolean update(User user) {
        USER_MAP.put(user.getId(), user);
        return true;
    }


}
