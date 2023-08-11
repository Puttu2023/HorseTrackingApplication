package com.horsetrackapp.horsetrack.repository;
import com.horsetrackapp.horsetrack.model.Horse;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HorseRepository extends CrudRepository<Horse, Integer> {
    List<Horse> findAll();
    Horse findByHorseNumberEquals(int horseNumber);
}
