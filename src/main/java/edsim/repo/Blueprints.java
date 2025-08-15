package edsim.repo;

import edsim.entity.*;

public class Blueprints extends Effects
{
    public Blueprints()
    {
        add(Effect.builder()
            .withName("Blast Resistant")
            .withType(ModuleType.ARMOUR)
            .withKineticResistance(-.12)
            .withThermalResistance(-.12)
            .withExplosiveResistance(.40));

        add(Effect.builder()
            .withName("Heavy Duty")
            .withType(ModuleType.ARMOUR)
            .withHullBoost(.32)
            .withKineticResistance(.05)
            .withThermalResistance(.05)
            .withExplosiveResistance(.05));

        add(Effect.builder()
            .withName("Kinetic Resistant")
            .withType(ModuleType.ARMOUR)
            .withKineticResistance(.40)
            .withThermalResistance(-.12)
            .withExplosiveResistance(-.12));

        add(Effect.builder()
            .withName("Lightweight")
            .withType(ModuleType.ARMOUR)
            .withHullBoost(-.05)
            .withKineticResistance(.15)
            .withThermalResistance(.15)
            .withExplosiveResistance(.15));

        add(Effect.builder()
            .withName("Thermal Resistant")
            .withType(ModuleType.ARMOUR)
            .withKineticResistance(-.12)
            .withThermalResistance(.40)
            .withExplosiveResistance(-.12));
    }
}
