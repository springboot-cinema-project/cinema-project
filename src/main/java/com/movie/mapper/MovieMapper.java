package com.movie.mapper;

import com.movie.domain.Movies;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieMapper {

    int insertMovie(Movies movies);
}
