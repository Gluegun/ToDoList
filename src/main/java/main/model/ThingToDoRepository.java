package main.model;

import java.util.*;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThingToDoRepository extends CrudRepository<ThingToDo, Integer> {

    List<ThingToDo> findByDescription(String description);


}
