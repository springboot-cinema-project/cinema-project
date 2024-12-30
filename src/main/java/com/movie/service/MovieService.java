package com.movie.service;

import com.movie.domain.Movies;
import com.movie.mapper.MovieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    @Autowired
    private final MovieMapper movieMapper;

    public long insertMovie(Movies movies) {
        return movieMapper.insertMovie(movies);
    }

    public List<Movies> movieManageList() {
        return movieMapper.movieManageList();
    }

    public Movies movieDetail(long id) {
        return movieMapper.movieDetail(id);
    }

    public long updateMovie(Movies movies) {
        return movieMapper.updateMovie(movies);
    }

    public long deleteMovie(long id) {
        return movieMapper.deleteMovie(id);
    }
}
