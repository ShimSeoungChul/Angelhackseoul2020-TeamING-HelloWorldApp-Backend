package com.angelhack2020.ing.dao;

import com.angelhack2020.ing.domain.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
