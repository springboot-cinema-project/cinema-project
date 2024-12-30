package com.movie.service;

import com.movie.domain.Events;
import com.movie.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    @Autowired
    private final EventMapper eventMapper;

    public long insertEvent(Events events) {
        return eventMapper.insertEvent(events);
    }

    public List<Events> eventManageList() {
        return eventMapper.eventManageList();
    }

    public Events eventDetail(long id) {
        return eventMapper.eventDetail(id);
    }

    public long updateEvent(Events events) {
        return eventMapper.updateEvent(events);
    }

    public long deleteEvent(long id) {
        return eventMapper.deleteEvent(id);
    }
}
