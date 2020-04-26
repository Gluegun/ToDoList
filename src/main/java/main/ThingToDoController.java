package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.ThingToDo;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ThingToDoController {

    @GetMapping("/toDo/")
    public List<ThingToDo> list() {
        return Storage.getAllThingsToDo();
    }

    @PostMapping("/toDo/")
    public int add(ThingToDo thingToDo) {
        return Storage.addThingToDo(thingToDo);
    }

    @GetMapping("/toDo/{id}")
    public ResponseEntity get(@PathVariable int id) {

        ThingToDo thingToDo = Storage.getThingToDoById(id);
        if (thingToDo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(thingToDo, HttpStatus.OK);
    }

    @DeleteMapping("/toDo/{id}")
    public ResponseEntity delete(@PathVariable int id) {


        ThingToDo thingToDo = Storage.deleteById(id);
        if (thingToDo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(thingToDo, HttpStatus.OK);
    }

    @PutMapping("/toDo/{id}")
    public ResponseEntity put(@PathVariable int id, ThingToDo thingToDo) {

        Storage.editThingToDoById(id, thingToDo);

        if (thingToDo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(thingToDo, HttpStatus.OK);
    }
}
