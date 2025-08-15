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
            .filter(effect -> effect.hasId(id))
            .findFirst()
            .orElse(null);
    }

    public Effect findByName(ModuleType type, String name)
    {
        return modules.stream()
            .filter(effect -> effect.hasType(type) && effect.hasName(name))
            .findFirst()
            .orElse(null);
    }
}
