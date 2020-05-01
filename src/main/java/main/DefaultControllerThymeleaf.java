package main;

import main.model.ThingToDo;
import main.model.ThingToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class DefaultControllerThymeleaf {

    @Autowired
    private ThingToDoRepository repository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<ThingToDo> iterableThingsToDo = repository.findAll();
        List<ThingToDo> thingsToDo = new ArrayList<>();
        iterableThingsToDo.forEach(thingsToDo::add);
        model.addAttribute("thingsToDo", thingsToDo);
        model.addAttribute("thingsToDoCount", thingsToDo.size());
        return "main";
    }

    @PostMapping("/")
    public String post(@RequestParam String description, Model model) {
        if (description.isEmpty()) {
            return "redirect:/";
        }
        ThingToDo thingToDo = new ThingToDo(description);
        repository.save(thingToDo);
        Iterable<ThingToDo> thingsToDo = repository.findAll();
        model.addAttribute("thingsToDo", thingsToDo);
        return "main";
    }


}
