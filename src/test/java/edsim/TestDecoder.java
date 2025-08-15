package edsim;

import static edsim.Matchers.equalToModule;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.*;
import org.junit.jupiter.api.*;
import edsim.entity.*;
import edsim.entity.Module;
import edsim.jenetics.*;
import edsim.repo.*;

public class TestDecoder
{
    private Experimentals experimentals;

    private Blueprints blueprints;

    @BeforeEach
    public void setup()
    {
        blueprints = new Blueprints();
        experimentals = new Experimentals();
    }

    @Test
    public void testDecode()
    {
        ShipDecoder decoder = new ShipDecoder(blueprints, experimentals);
        ShipSpec spec = new ShipSpec();
        Ship ship = decoder.decode(spec, List.of(
            idOfBlueprint("Blast Resistant"),
            idOfExperimental("Angled Plating")));

        assertThat(ship.hull, equalToModule(Module.builder()
            .withType(ModuleType.ARMOUR)
            .withBlueprint(blueprint("Blast Resistant"))
            .withExperimental(experimental("Angled Plating"))));
    }

    private Effect blueprint(String name)
    {
        return blueprints.getByName(name);
    }

    private Effect experimental(String name)
    {
        return experimentals.getByName(name);
    }

    private int idOfBlueprint(String name)
    {
        return blueprint(name).getId();
    }

    private int idOfExperimental(String name)
    {
        return experimentals.getByName(name).getId();
    }
}
