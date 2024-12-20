package com.movie.service;

import com.movie.domain.Movies;
import com.movie.mapper.MovieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

    @Autowired
    private final MovieMapper movieMapper;

    public int insertMovie(Movies movies) {
        return movieMapper.insertMovie(movies);
    }
}
