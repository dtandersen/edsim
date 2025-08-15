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

    public Effect get(int index)
    {
        return modules.get(index);
    }

    @Deprecated
    public int get(String string)
    {
        for (int i = 0; i < modules.size(); i++)
        {
            if (modules.get(i).getName().equals(string))
            {
                return i;
            }
        }
        throw new NoSuchElementException("No blueprint found with name: " + string);
    }

    public Effect getByName(String name)
    {
        for (int i = 0; i < modules.size(); i++)
        {
            if (modules.get(i).getName().equals(name))
            {
                return modules.get(i);
            }
        }
        throw new NoSuchElementException("No blueprint found with name: " + name);
    }
}
