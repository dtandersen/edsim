import java.util.*;

public class Experimentals
{
    private ArrayList<Blueprint> modules;

    public Experimentals()
    {
        modules = new ArrayList<>();

        add(Blueprint.builder()
            .withName("Angled Plating")
            .withHullBoost(-.03)
            .withKineticResistance(.08));

        add(Blueprint.builder()
            .withName("Deep Plating")
            .withHullBoost(.08)
            .withKineticResistance(-.03)
            .withThermalResistance(-.03)
            .withExplosiveResistance(-.03));

        add(Blueprint.builder()
            .withName("Layered Plating")
            .withHullBoost(-.03)
            .withExplosiveResistance(.08));

        add(Blueprint.builder()
            .withName("Reflective Plating")
            .withHullBoost(-.03)
            .withThermalResistance(.08));
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
        throw new NoSuchElementException("No experimental found with name: " + string);
    }
}
