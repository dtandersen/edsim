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

        assertThat(ship.utilities.get(0), equalToModule(Module.builder()
            .withType(ModuleType.SHIELD_BOOSTER)
            .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER, "Heavy Duty"))
            .withExperimental(experimental(ModuleType.SHIELD_BOOSTER, "Super Capacitors"))));
    }

    @Test
    public void shipHasTwoOptionalInternals()
    {
        ShipDecoder decoder = new ShipDecoder(blueprints, experimentals);
        ShipSpec spec = ShipSpec.builder()
            .withUtility(2)
            .build();

        Ship ship = decoder.decode(spec, List.of(
            idOfBlueprint(ModuleType.ARMOUR, "Heavy Duty"),
            idOfExperimental(ModuleType.ARMOUR, "Deep Plating"),
            idOfBlueprint(ModuleType.SHIELD_BOOSTER, "Heavy Duty"),
            idOfExperimental(ModuleType.SHIELD_BOOSTER, "Super Capacitors"),
            idOfBlueprint(ModuleType.SHIELD_BOOSTER, "Resistance Augmented"),
            idOfExperimental(ModuleType.SHIELD_BOOSTER, "Thermo Block")));

        assertThat(ship.hull, equalToModule(Module.builder()
            .withType(ModuleType.ARMOUR)
            .withBlueprint(blueprint(ModuleType.ARMOUR, "Heavy Duty"))
            .withExperimental(experimental(ModuleType.ARMOUR, "Deep Plating"))));

        assertThat(ship.utilities.get(0), equalToModule(Module.builder()
            .withType(ModuleType.SHIELD_BOOSTER)
            .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER, "Heavy Duty"))
            .withExperimental(experimental(ModuleType.SHIELD_BOOSTER, "Super Capacitors"))));

        assertThat(ship.utilities.get(1), equalToModule(Module.builder()
            .withType(ModuleType.SHIELD_BOOSTER)
            .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER, "Resistance Augmented"))
            .withExperimental(experimental(ModuleType.SHIELD_BOOSTER, "Thermo Block"))));
    }

    @Test
    public void skipsMismatchedModules()
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

        assertThat(ship.utilities.size(), is(0));
    }

    @Test
    public void skipsInvalidIds()
    {
        ShipDecoder decoder = new ShipDecoder(blueprints, experimentals);
        ShipSpec spec = ShipSpec.builder()
            .withUtility(1)
            .build();

        Ship ship = decoder.decode(spec, List.of(9999, 9999, 9999, 9999));

        assertThat(ship.hull, equalToModule(Module.builder()
            .withType(ModuleType.ARMOUR)));

        assertThat(ship.utilities.size(), is(0));
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
