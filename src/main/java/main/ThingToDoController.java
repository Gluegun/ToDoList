package main;

import main.model.ThingToDo;
import main.model.ThingToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ThingToDoController {

    @Autowired
    private ThingToDoRepository repository;

    @GetMapping("/toDo/")
    public List<ThingToDo> getList() {

        Iterable<ThingToDo> thingToDoIterable = repository.findAll();
        List<ThingToDo> thingsToDo = new ArrayList<>();
        thingToDoIterable.forEach(thingsToDo::add);

        return thingsToDo;

    }

    @PostMapping("/toDo/")
    public int post(ThingToDo thingToDo) {

        ThingToDo newThingToDo = repository.save(thingToDo);
        return newThingToDo.getId();

    }

    @GetMapping("/toDo/{id}")
    public ResponseEntity<ThingToDo> getById(@PathVariable int id) {

        Optional<ThingToDo> optionalThingToDo = repository.findById(id);

        return optionalThingToDo.map(thingToDo -> new ResponseEntity<>(thingToDo, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }


    @DeleteMapping("/toDo/{id}")
    public ResponseEntity<ThingToDo> deleteById(@PathVariable int id) {

        Optional<ThingToDo> thingToDo = repository.findById(id);
        if (thingToDo.isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    @PutMapping("/toDo/{id}")
    public ResponseEntity<ThingToDo> putById(@PathVariable int id, ThingToDo thingToDo) {

        Optional<ThingToDo> optionalThingToDo = repository.findById(id);

        if (optionalThingToDo.isPresent()) {
            ThingToDo save = repository.save(thingToDo);
            return new ResponseEntity<>(save, HttpStatus.OK);
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
