package edsim.repo;

import java.util.*;
import edsim.entity.*;

public class Effects
{
    private ArrayList<Effect> modules;

    public Effects()
    {
        modules = new ArrayList<>();
    }

    public void add(Effect.EffectBuilder blueprint)
    {
        modules.add(blueprint
            .withId(modules.size())
            .build());
    }

    public Effect findById(int id)
    {
        return modules.stream()
            .filter(effect -> effect.getId() == id)
            .findFirst()
            .orElse(null);
    }

    public Effect findByName(ModuleType type, String name)
    {
        return modules.stream()
            .filter(effect -> effect.getType() == type && effect.getName().equals(name))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("No effect found for type: " + type + " and name: " + name));
    }
}
