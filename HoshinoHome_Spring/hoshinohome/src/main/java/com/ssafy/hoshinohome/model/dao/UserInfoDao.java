package com.ssafy.hoshinohome.model.dao;

import com.ssafy.hoshinohome.model.dto.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserInfoDao {

    UserInfo findByUsername(@Param("username") String username);
    void insertUser(UserInfo user);
    void updateUser(UserInfo user);
    void deleteUser(@Param("username") String username);

    @Select("SELECT * FROM userinfo")
    List<UserInfo> findAll();
}
