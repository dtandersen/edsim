import java.util.*;

public class Blueprints
{
    private ArrayList<Blueprint> modules;

    public Blueprints()
    {
        modules = new ArrayList<>();

        add(Blueprint.builder()
            .withName("Blast Resistant")
            .withKineticResistance(-.12)
            .withThermalResistance(-.12)
            .withExplosiveResistance(.40));

        add(Blueprint.builder()
            .withName("Heavy Duty")
            .withHullBoost(.32)
            .withKineticResistance(.05)
            .withThermalResistance(.05)
            .withExplosiveResistance(.05));

        add(Blueprint.builder()
            .withName("Kinetic Resistant")
            .withKineticResistance(.40)
            .withThermalResistance(-.12)
            .withExplosiveResistance(-.12));

        add(Blueprint.builder()
            .withName("Lightweight")
            .withHullBoost(-.05)
            .withKineticResistance(.15)
            .withThermalResistance(.15)
            .withExplosiveResistance(.15));

        add(Blueprint.builder()
            .withName("Thermal Resistant")
            .withKineticResistance(-.12)
            .withThermalResistance(.40)
            .withExplosiveResistance(-.12));
    }

    public void add(Blueprint.BlueprintBuilder blueprint)
    {
        modules.add(blueprint.build());
    }

    public Blueprint get(int index)
    {
        return modules.get(index);
    }

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

    public Blueprint getByName(String string)
    {
        for (int i = 0; i < modules.size(); i++)
        {
            if (modules.get(i).getName().equals(string))
            {
                return modules.get(i);
            }
        }
        throw new NoSuchElementException("No blueprint found with name: " + string);
    }

}
