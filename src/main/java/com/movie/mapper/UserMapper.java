package com.movie.mapper;

import com.movie.domain.Role;
import com.movie.domain.SocialProvider;
import com.movie.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO users (email, password, name, phone, birth) " +
            "VALUES (#{email}, #{password}, #{name}, #{phone}, #{birth})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

    @Insert("INSERT INTO users (email, name, social_id, social_provider, role, created_at, updated_at) " +
            "VALUES (#{email}, #{name}, #{socialId}, #{socialProvider}, #{role}, #{createdAt}, #{updatedAt})")
    void insertSocialUser(User user);

    @Update("UPDATE users SET social_id = #{socialId}, social_provider = #{socialProvider}, updated_at = #{updatedAt} WHERE email = #{email}")
    void updateSocialUser(User user);

    @Select("SELECT * FROM users WHERE email = #{email}")
    @Results({
            @Result(property = "role", column = "role", javaType = Role.class, typeHandler = com.movie.handler.EnumTypeHandler.class),
            @Result(property = "socialProvider", column = "social_provider", javaType = SocialProvider.class, typeHandler = com.movie.handler.EnumTypeHandler.class)
    })
    Optional<User> findByEmail(String email);

    @Select("SELECT COUNT(*) > 0 FROM users WHERE email = #{email}")
    boolean existsByEmail(String email);
}