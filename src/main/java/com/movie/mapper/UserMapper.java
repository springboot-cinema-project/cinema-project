package com.movie.mapper;

import com.movie.domain.User;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface UserMapper {

    @InsertProvider(type = UserSqlProvider.class, method = "insertUser")
    void insertUser(User user);

    @Select("select * from users where email = #{email}")
    Optional<User> findByEmail(String email);

    @Select("select count(*) > 0 from users where email = #{email}")
    boolean existsByEmail(String email);

}
