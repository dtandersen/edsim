package edsim.repo;

import java.util.*;
import java.util.stream.*;
import edsim.entity.*;

public class Effects
{
    private ArrayList<Effect> modules;

    public Effects()
    {
        modules = new ArrayList<>();
    }

    public int size()
    {
        return modules.size();
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

    public List<Effect> findAllBySlot(SlotType slotType)
    {
        List<Effect> result = modules.stream()
            .filter(effect -> effect.hasSlot(slotType))
            .collect(Collectors.toList());

        return result;
    }

    public List<Effect> findAllByModule(ModuleType moduleType)
    {
        List<Effect> result = modules.stream()
            .filter(effect -> effect.hasType(moduleType))
            .collect(Collectors.toList());

        return result;
    }
}
