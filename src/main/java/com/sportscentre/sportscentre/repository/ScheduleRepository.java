package com.sportscentre.sportscentre.repository;

import com.sportscentre.sportscentre.model.Activity;
import com.sportscentre.sportscentre.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.CollectionTable;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
List<Schedule> findAllByActivity(Activity activity);
}
