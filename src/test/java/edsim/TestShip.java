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
                .withType(ModuleType.ARMOUR)
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
                .withType(ModuleType.ARMOUR)
                .withBlueprint(blueprint(ModuleType.ARMOUR, "Heavy Duty"))
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
                .withType(ModuleType.ARMOUR)
                .withBlueprint(blueprint(ModuleType.ARMOUR, "Heavy Duty"))
                .withExperimental(experimental(ModuleType.ARMOUR, "Deep Plating"))
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
                .withType(ModuleType.ARMOUR)
                // .withBlueprint(blueprint(ModuleType.ARMOUR, "Heavy Duty"))
                // .withExperimental(experimental(ModuleType.ARMOUR, "Deep
                // Plating"))
                .build())
            .withUtilities(Collections.emptyList())
            .build();

        assertThat(ship.getTotalShield(), closeTo(349.9, 0.05));
    }

    @Test
    public void calculatesShieldStrengthWithBooster()
    {
        Ship ship = Ship.builder()
            .withBaseArmour(280)
            .withBulkheadHullBoost(.80)
            .withShields(349.9)
            .withBulkhead(Module.builder()
                .withType(ModuleType.ARMOUR)
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

        assertThat(ship.getTotalShield(), closeTo(419.9, 0.05));
    }

    @Test
    public void calculatesShieldStrengthWithHeavyDuty()
    {
        Ship ship = Ship.builder()
            .withBaseArmour(280)
            .withBulkheadHullBoost(.80)
            .withShields(349.9)
            .withBulkhead(Module.builder()
                .withType(ModuleType.ARMOUR)
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

        assertThat(ship.getTotalShield(), closeTo(579.4, 0.05));
    }

    @Test
    public void calculatesShieldStrengthWithHeavyDutySuperCapacitors()
    {
        Ship ship = Ship.builder()
            .withBaseArmour(280)
            .withBulkheadHullBoost(.80)
            .withShields(349.9)
            .withBulkhead(Module.builder()
                .withType(ModuleType.ARMOUR)
                .build())
            .withUtilities(List.of(
                Module.builder()
                    .withType(ModuleType.SHIELD_BOOSTER)
                    .withBlueprint(blueprint(ModuleType.SHIELD_BOOSTER, "Heavy Duty"))
                    .withExperimental(experimental(ModuleType.SHIELD_BOOSTER, "Super Capacitors"))
                    .build()))
            .build();

        assertThat(ship.getTotalShield(), closeTo(608.4, 0.05));
    }

    @Test
    public void calculatesShieldStrengthWithTwoBoosters()
    {
        Ship ship = Ship.builder()
            .withBaseArmour(280)
            .withBulkheadHullBoost(.80)
            .withShields(349.9)
            .withBulkhead(Module.builder()
                .withType(ModuleType.ARMOUR)
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

        assertThat(ship.getTotalShield(), closeTo(489.9, 0.05));
    }

    @Test
    public void calculatesShieldStrengthWithTwoEngineeredBoosters()
    {
        Ship ship = Ship.builder()
            .withBaseArmour(280)
            .withBulkheadHullBoost(.80)
            .withShields(349.9)
            .withBulkhead(Module.builder()
                .withType(ModuleType.ARMOUR)
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

        assertThat(ship.getTotalShield(), closeTo(866.9, 0.05));
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
                .withType(ModuleType.ARMOUR)
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
                .withType(ModuleType.ARMOUR)
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
        assertThat(ship.getTotalShield(), closeTo(481.5, 0.05));
    }
}
