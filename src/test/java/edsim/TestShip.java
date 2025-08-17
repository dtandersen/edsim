package edsim;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import java.util.*;
import org.junit.jupiter.api.*;
import edsim.entity.*;
import edsim.entity.Module;

public class TestShip extends EdSimTest
{
    @BeforeEach
    public void setup()
    {

    }

    @Test
    public void createsAShip()
    {
        Ship ship = Ship.builder()
            .withBaseArmour(280)
            .withBulkheadHullBoost(.80)
            .withShields(1000)
            .withBulkhead(Module.builder()
                .withType(ModuleType.BULKHEAD)
                // .withBlueprint(blueprint(ModuleType.ARMOUR, "Heavy Duty"))
                // .withExperimental(experimental(ModuleType.ARMOUR, "Deep
                // Plating"))
                .build())
            .withUtilities(List.of(Module.builder()
                .withType(ModuleType.SHIELD_BOOSTER)
                .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER, "Heavy Duty"))
                .withExperimental(experimental(ModuleType.SHIELD_BOOSTER, "Super Capacitors"))
                .build()))
            .build();

        assertThat(ship.getTotalArmour(), closeTo(504.0, 0.05));
    }

    @Test
    public void createsAShip2()
    {
        Ship ship = Ship.builder()
            .withBaseArmour(280)
            .withBulkheadHullBoost(.80)
            .withShields(1000)
            .withBulkhead(Module.builder()
                .withType(ModuleType.BULKHEAD)
                .withBlueprint(blueprint(ModuleType.BULKHEAD, "Heavy Duty"))
                // .withExperimental(experimental(ModuleType.ARMOUR, "Deep
                // Plating"))
                .build())
            .withUtilities(List.of(Module.builder()
                .withType(ModuleType.SHIELD_BOOSTER)
                .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER, "Heavy Duty"))
                .withExperimental(experimental(ModuleType.SHIELD_BOOSTER, "Super Capacitors"))
                .build()))
            .build();

        assertThat(ship.getTotalArmour(), closeTo(665.3, 0.05));
    }

    @Test
    public void createsAShip3()
    {
        Ship ship = Ship.builder()
            .withBaseArmour(280)
            .withBulkheadHullBoost(.80)
            .withShields(1000)
            .withBulkhead(Module.builder()
                .withType(ModuleType.BULKHEAD)
                .withBlueprint(blueprint(ModuleType.BULKHEAD, "Heavy Duty"))
                .withExperimental(experimental(ModuleType.BULKHEAD, "Deep Plating"))
                .build())
            .withUtilities(Collections.emptyList())
            .build();

        assertThat(ship.getTotalArmour(), closeTo(718.5, 0.1));
    }

    @Test
    public void calculatesShieldStrengthWithNoBoosters()
    {
        Ship ship = Ship.builder()
            .withBaseArmour(280)
            .withBulkheadHullBoost(.80)
            .withShields(349.9)
            .withBulkhead(Module.builder()
                .withType(ModuleType.BULKHEAD)
                // .withBlueprint(blueprint(ModuleType.ARMOUR, "Heavy Duty"))
                // .withExperimental(experimental(ModuleType.ARMOUR, "Deep
                // Plating"))
                .build())
            .withUtilities(Collections.emptyList())
            .build();

        assertThat(ship.getTotalShields(), closeTo(349.9, 0.05));
    }

    @Test
    public void calculatesShieldStrengthWithBooster()
    {
        Ship ship = Ship.builder()
            .withBaseArmour(280)
            .withBulkheadHullBoost(.80)
            .withShields(349.9)
            .withBulkhead(Module.builder()
                .withType(ModuleType.BULKHEAD)
                // .withBlueprint(blueprint(ModuleType.ARMOUR, "Heavy Duty"))
                // .withExperimental(experimental(ModuleType.ARMOUR, "Deep
                // Plating"))
                .build())
            .withUtilities(List.of(
                Module.builder()
                    .withType(ModuleType.SHIELD_BOOSTER)
                    // .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER,
                    // "Heavy Duty"))
                    // .withExperimental(experimental(ModuleType.SHIELD_BOOSTER,
                    // "Super Capacitors"))
                    .build()))
            .build();

        assertThat(ship.getTotalShields(), closeTo(419.9, 0.05));
    }

    @Test
    public void calculatesShieldStrengthWithHeavyDuty()
    {
        Ship ship = Ship.builder()
            .withBaseArmour(280)
            .withBulkheadHullBoost(.80)
            .withShields(349.9)
            .withBulkhead(Module.builder()
                .withType(ModuleType.BULKHEAD)
                // .withBlueprint(blueprint(ModuleType.ARMOUR, "Heavy Duty"))
                // .withExperimental(experimental(ModuleType.ARMOUR, "Deep
                // Plating"))
                .build())
            .withUtilities(List.of(
                Module.builder()
                    .withType(ModuleType.SHIELD_BOOSTER)
                    .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER, "Heavy Duty"))
                    // .withExperimental(experimental(ModuleType.SHIELD_BOOSTER,
                    // "Super Capacitors"))
                    .build()))
            .build();

        assertThat(ship.getTotalShields(), closeTo(579.4, 0.05));
    }

    @Test
    public void calculatesShieldStrengthWithHeavyDutySuperCapacitors()
    {
        Ship ship = Ship.builder()
            .withBaseArmour(280)
            .withBulkheadHullBoost(.80)
            .withShields(349.9)
            .withBulkhead(Module.builder()
                .withType(ModuleType.BULKHEAD)
                .build())
            .withUtilities(List.of(
                Module.builder()
                    .withType(ModuleType.SHIELD_BOOSTER)
                    .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER, "Heavy Duty"))
                    .withExperimental(experimental(ModuleType.SHIELD_BOOSTER, "Super Capacitors"))
                    .build()))
            .build();

        assertThat(ship.getTotalShields(), closeTo(608.4, 0.05));
    }

    @Test
    public void calculatesShieldStrengthWithTwoBoosters()
    {
        Ship ship = Ship.builder()
            .withBaseArmour(280)
            .withBulkheadHullBoost(.80)
            .withShields(349.9)
            .withBulkhead(Module.builder()
                .withType(ModuleType.BULKHEAD)
                .build())
            .withUtilities(List.of(
                Module.builder()
                    .withType(ModuleType.SHIELD_BOOSTER)
                    // .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER,
                    // "Heavy Duty"))
                    // .withExperimental(experimental(ModuleType.SHIELD_BOOSTER,
                    // "Super Capacitors"))
                    .build(),
                Module.builder()
                    .withType(ModuleType.SHIELD_BOOSTER)
                    // .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER,
                    // "Heavy Duty"))
                    // .withExperimental(experimental(ModuleType.SHIELD_BOOSTER,
                    // "Super Capacitors"))
                    .build()))
            .build();

        assertThat(ship.getTotalShields(), closeTo(489.9, 0.05));
    }

    @Test
    public void calculatesShieldStrengthWithTwoEngineeredBoosters()
    {
        Ship ship = Ship.builder()
            .withBaseArmour(280)
            .withBulkheadHullBoost(.80)
            .withShields(349.9)
            .withBulkhead(Module.builder()
                .withType(ModuleType.BULKHEAD)
                .build())
            .withUtilities(List.of(
                Module.builder()
                    .withType(ModuleType.SHIELD_BOOSTER)
                    .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER,
                        "Heavy Duty"))
                    .withExperimental(experimental(ModuleType.SHIELD_BOOSTER,
                        "Super Capacitors"))
                    .build(),
                Module.builder()
                    .withType(ModuleType.SHIELD_BOOSTER)
                    .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER,
                        "Heavy Duty"))
                    .withExperimental(experimental(ModuleType.SHIELD_BOOSTER,
                        "Super Capacitors"))
                    .build()))
            .build();

        assertThat(ship.getTotalShields(), closeTo(866.9, 0.05));
    }

    @Test
    public void baseShieldResists()
    {
        Ship ship = Ship.builder()
            .withBaseArmour(280)
            .withBulkheadHullBoost(.80)
            .withShields(349.9)
            .withBaseShieldKineticResist(.40)
            .withBaseShieldThermalResist(-.20)
            .withBaseShieldExplosiveResist(.50)
            .withBulkhead(Module.builder()
                .withType(ModuleType.BULKHEAD)
                .build())
            .withUtilities(List.of(
                Module.builder()
                    .withType(ModuleType.SHIELD_BOOSTER)
                    .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER,
                        "Heavy Duty"))
                    .withExperimental(experimental(ModuleType.SHIELD_BOOSTER,
                        "Super Capacitors"))
                    .build(),
                Module.builder()
                    .withType(ModuleType.SHIELD_BOOSTER)
                    .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER,
                        "Heavy Duty"))
                    .withExperimental(experimental(ModuleType.SHIELD_BOOSTER,
                        "Super Capacitors"))
                    .build()))
            .build();

        assertThat(ship.getBaseShieldKineticResist(), closeTo(.40, 0.05));
        assertThat(ship.getBaseShieldThermalResist(), closeTo(-0.2, 0.05));
        assertThat(ship.getBaseShieldExplosiveResist(), closeTo(0.5, 0.05));
    }

    @Test
    public void augmentedShieldBoostersResists()
    {
        Ship ship = Ship.builder()
            .withBaseArmour(280)
            .withBulkheadHullBoost(.80)
            .withShields(349.9)
            .withBaseShieldKineticResist(.40)
            .withBaseShieldThermalResist(-.20)
            .withBaseShieldExplosiveResist(.50)
            .withBulkhead(Module.builder()
                .withType(ModuleType.BULKHEAD)
                .build())
            .withUtilities(List.of(
                Module.builder()
                    .withType(ModuleType.SHIELD_BOOSTER)
                    .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER, "Resistance Augmented"))
                    .withExperimental(experimental(ModuleType.SHIELD_BOOSTER, "Thermo Block"))
                    .build(),
                Module.builder()
                    .withType(ModuleType.SHIELD_BOOSTER)
                    .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER, "Resistance Augmented"))
                    .withExperimental(experimental(ModuleType.SHIELD_BOOSTER, "Thermo Block"))
                    .build()))
            .build();

        assertThat(ship.getTotalShieldKineticResist(), closeTo(.583, 0.05));
        assertThat(ship.getTotalShieldThermalResist(), closeTo(.183, 0.05));
        assertThat(ship.getTotalShieldExplosiveResist(), closeTo(.653, 0.05));
        assertThat(ship.getTotalShields(), closeTo(481.5, 0.05));
    }

    @Test
    public void augmentedShieldBoostersResists2()
    {
        Ship ship = Ship.builder()
            .withBaseArmour(280)
            .withBulkheadHullBoost(.80)
            .withShields(349.9)
            .withBaseShieldKineticResist(.40)
            .withBaseShieldThermalResist(-.20)
            .withBaseShieldExplosiveResist(.50)
            .withBulkhead(Module.builder()
                .withType(ModuleType.BULKHEAD)
                .build())
            .withUtilities(List.of(
                Module.builder()
                    .withType(ModuleType.SHIELD_BOOSTER)
                    .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER, "Resistance Augmented"))
                    .withExperimental(experimental(ModuleType.SHIELD_BOOSTER, "Thermo Block"))
                    .build(),
                Module.builder()
                    .withType(ModuleType.SHIELD_BOOSTER)
                    .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER, "Resistance Augmented"))
                    .withExperimental(experimental(ModuleType.SHIELD_BOOSTER, "Blast Block"))
                    .build(),
                Module.builder()
                    .withType(ModuleType.SHIELD_BOOSTER)
                    .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER, "Resistance Augmented"))
                    .withExperimental(experimental(ModuleType.SHIELD_BOOSTER, "Force Block"))
                    .build()))
            .build();

        assertThat(ship.getTotalShieldKineticResist(), closeTo(62.2 / 100, 0.05));
        assertThat(ship.getTotalShieldThermalResist(), closeTo(24.4 / 100, 0.05));
        assertThat(ship.getTotalShieldExplosiveResist(), closeTo(68.5 / 100, 0.05));
        assertThat(ship.getTotalShields(), closeTo(547.3, 0.5));
        assertThat(ship.getTotalShieldKineticEhp(), closeTo(1447.4, 0.5));
        assertThat(ship.getTotalShieldThermalEhp(), closeTo(723.7, 0.5));
        assertThat(ship.getTotalShieldExplosiveEhp(), closeTo(1736.8, 0.5));
    }

    /**
     * Genes: [1, 1, 8, 4, 5, 6, 5, 4, 8, 7, 8, 4]
     * Armour: 0.0
     * Shields: 1029.5
     * Kinetic Resist: 31.2% EHP: 1495
     * Thermal Resist: 34.7% EHP: 1576 <-- incorrect
     * Explosive Resist: 46.0% EHP: 1907
     * Armour: Heavy Duty / Deep Plating
     * Utility: Thermal Resistant / Blast Block
     * Utility: Heavy Duty / Super Capacitors
     * Utility: Heavy Duty / Blast Block
     * Utility: Thermal Resistant / Thermo Block
     * Utility: Thermal Resistant / Blast Block
     */
    @Test
    public void augmentedShieldBoostersResists3()
    {
        Ship ship = Ship.builder()
            // .withBaseArmour(280)
            // .withBulkheadHullBoost(.80)
            .withShields(349.9)
            .withBaseShieldKineticResist(.40)
            .withBaseShieldThermalResist(-.20)
            .withBaseShieldExplosiveResist(.50)
            .withBulkhead(Module.builder()
                .withType(ModuleType.BULKHEAD)
                .withBlueprint(blueprint(ModuleType.BULKHEAD, "Heavy Duty"))
                .withExperimental(experimental(ModuleType.BULKHEAD, "Deep Plating"))
                .build())
            .withUtilities(List.of(
                Module.builder()
                    .withType(ModuleType.SHIELD_BOOSTER)
                    .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER, "Thermal Resistant"))
                    .withExperimental(experimental(ModuleType.SHIELD_BOOSTER, "Blast Block"))
                    .build(),
                Module.builder()
                    .withType(ModuleType.SHIELD_BOOSTER)
                    .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER, "Heavy Duty"))
                    .withExperimental(experimental(ModuleType.SHIELD_BOOSTER, "Super Capacitors"))
                    .build(),
                Module.builder()
                    .withType(ModuleType.SHIELD_BOOSTER)
                    .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER, "Heavy Duty"))
                    .withExperimental(experimental(ModuleType.SHIELD_BOOSTER, "Blast Block"))
                    .build(),
                Module.builder()
                    .withType(ModuleType.SHIELD_BOOSTER)
                    .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER, "Thermal Resistant"))
                    .withExperimental(experimental(ModuleType.SHIELD_BOOSTER, "Thermo Block"))
                    .build(),
                Module.builder()
                    .withType(ModuleType.SHIELD_BOOSTER)
                    .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER, "Thermal Resistant"))
                    .withExperimental(experimental(ModuleType.SHIELD_BOOSTER, "Blast Block"))
                    .build()))
            .build();

        assertThat(ship.getTotalShieldKineticResist(), closeTo(31.2 / 100, 0.05));
        assertThat(ship.getTotalShieldThermalResist(), closeTo(33.6 / 100, 0.05));
        assertThat(ship.getTotalShieldExplosiveResist(), closeTo(46.0 / 100, 0.05));
        assertThat(ship.getTotalShields(), closeTo(1029.5, 0.5));
        // assertThat(ship.getTotalShieldKineticEhp(), closeTo(1447.4, 0.5));
        // assertThat(ship.getTotalShieldThermalEhp(), closeTo(723.7, 0.5));
        // assertThat(ship.getTotalShieldExplosiveEhp(), closeTo(1736.8, 0.5));
    }
}
