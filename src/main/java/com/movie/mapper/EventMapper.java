package com.movie.mapper;

import com.movie.domain.Events;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventMapper {

    @Insert("insert into events (event_title, event_content, event_img, event_start_date, event_end_date, coupon_id)" +
            " values (#{eventTitle}, #{eventContent}, #{eventImg}, #{eventStartDate}, #{eventEndDate}, #{couponId})")
    public long insertEvent(Events events);

    public List<Events> eventManageList();
}
