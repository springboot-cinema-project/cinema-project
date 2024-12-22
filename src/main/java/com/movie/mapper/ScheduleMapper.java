package com.movie.mapper;

import com.movie.domain.Schedules;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface ScheduleMapper {

    @Insert("INSERT INTO schedules (screen_id, movie_id, watch_date, start_time, end_time) values (#{screenId}, #{movieId}, #{watchDate}, #{startTime}, #{endTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public long insertSchedule(Schedules schedules);
}
