package main;

import main.model.ThingToDo;
import main.model.ThingToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
public class DefaultControllerMustache {

    @Autowired
    private ThingToDoRepository repository;

    @GetMapping
    public String get(Map<String, Object> model) {
        Iterable<ThingToDo> thingsToDo = repository.findAll();
        model.put("thingsToDo", thingsToDo);
        return "main";
    }

    @PostMapping
    public String post(@RequestParam String description, Map<String, Object> model) {
        if (description.isEmpty()) {
            return "redirect:/";
        }
        ThingToDo thingToDo = new ThingToDo(description);
        repository.save(thingToDo);
        Iterable<ThingToDo> thingsToDo = repository.findAll();
        model.put("thingsToDo", thingsToDo);
        return "main";
    }
    

}
