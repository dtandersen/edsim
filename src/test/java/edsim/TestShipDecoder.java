package edsim;

import static edsim.Matchers.equalToModule;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import java.util.*;
import org.junit.jupiter.api.*;
import edsim.entity.*;
import edsim.entity.Module;
import edsim.repo.*;

public class TestShipDecoder
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
    public void createsAShip()
    {
        ShipDecoder decoder = new ShipDecoder(blueprints, experimentals);
        ShipSpec spec = ShipSpec.builder()
            .withUtility(1)
            .build();

        Ship ship = decoder.decode(spec, List.of(
            idOfBlueprint(ModuleType.ARMOUR, "Blast Resistant"),
            idOfExperimental(ModuleType.ARMOUR, "Angled Plating"),
            idOfBlueprint(ModuleType.SHIELD_BOOSTER, "Heavy Duty"),
            idOfExperimental(ModuleType.SHIELD_BOOSTER, "Super Capacitors")));

        assertThat(ship.hull, equalToModule(Module.builder()
            .withType(ModuleType.ARMOUR)
            .withBlueprint(blueprint(ModuleType.ARMOUR, "Blast Resistant"))
            .withExperimental(experimental(ModuleType.ARMOUR, "Angled Plating"))));

        assertThat(ship.optionalInternals.get(0), equalToModule(Module.builder()
            .withType(ModuleType.SHIELD_BOOSTER)
            .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER, "Heavy Duty"))
            .withExperimental(experimental(ModuleType.SHIELD_BOOSTER, "Super Capacitors"))));
    }

    @Test
    public void ignoresInvalidModules()
    {
        ShipDecoder decoder = new ShipDecoder(blueprints, experimentals);
        ShipSpec spec = ShipSpec.builder()
            .withUtility(1)
            .build();

        Ship ship = decoder.decode(spec, List.of(
            idOfBlueprint(ModuleType.SHIELD_BOOSTER, "Heavy Duty"),
            idOfExperimental(ModuleType.SHIELD_BOOSTER, "Super Capacitors"),
            idOfBlueprint(ModuleType.ARMOUR, "Blast Resistant"),
            idOfExperimental(ModuleType.ARMOUR, "Angled Plating")));

        assertThat(ship.hull, equalToModule(Module.builder()
            .withType(ModuleType.ARMOUR)));

        assertThat(ship.optionalInternals.size(), is(0));
    }

    private Effect blueprint(ModuleType type, String name)
    {
        return blueprints.findByName(type, name);
    }

    private Effect experimental(ModuleType type, String name)
    {
        return experimentals.findByName(type, name);
    }

    private int idOfBlueprint(ModuleType type, String name)
    {
        return blueprint(type, name).getId();
    }

    private int idOfExperimental(ModuleType type, String name)
    {
        return experimental(type, name).getId();
    }
}
