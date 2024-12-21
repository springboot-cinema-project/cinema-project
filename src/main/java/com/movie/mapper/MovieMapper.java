package com.movie.mapper;

import com.movie.domain.Movies;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MovieMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    public long insertMovie(Movies movies);

    @Select("SELECT * FROM movies ORDER BY id DESC")
    public List<Movies> movieManageList();

    @Select("SELECT * FROM movies WHERE id = #{id}")
    public Movies movieDetail(long id);

    public long updateMovie(Movies movies);

    @Delete("DELETE FROM movies WHERE id = #{id}")
    public long deleteMovie(long id);
}
