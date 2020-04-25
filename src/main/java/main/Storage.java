package main;

import response.ThingToDo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {

    private static int currentId = 1;
    private static Map<Integer, ThingToDo> thingsToDo = new HashMap<>();

    public static List<ThingToDo> getAllThingsToDo() {

        return new ArrayList<>(thingsToDo.values());
    }

    public static int addThingToDo(ThingToDo thingToDo) {
        int id = currentId;
        thingToDo.setId(id);
        thingsToDo.put(id, thingToDo);
        return id;
    }

    public static ThingToDo getThingToDoById(int id) {

        if(thingsToDo.containsKey(id)) {
            return thingsToDo.get(id);
        }
        return null;
    }

    public static ThingToDo deleteById(int id) {
        if (thingsToDo.containsKey(id)) {
            return thingsToDo.remove(id);
        }
        return null;
    }
}
