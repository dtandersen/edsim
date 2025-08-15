package edsim.repo;

import edsim.entity.*;

public class Experimentals extends Effects
{
    public Experimentals()
    {
        add(Effect.builder()
            .withName("Angled Plating")
            .withType(ModuleType.ARMOUR)
            .withHullBoost(-.03)
            .withKineticResistance(.08));

        add(Effect.builder()
            .withName("Deep Plating")
            .withType(ModuleType.ARMOUR)
            .withHullBoost(.08)
            .withKineticResistance(-.03)
            .withThermalResistance(-.03)
            .withExplosiveResistance(-.03));

        add(Effect.builder()
            .withName("Layered Plating")
            .withType(ModuleType.ARMOUR)
            .withHullBoost(-.03)
            .withExplosiveResistance(.08));

        add(Effect.builder()
            .withName("Reflective Plating")
            .withType(ModuleType.ARMOUR)
            .withHullBoost(-.03)
            .withThermalResistance(.08));
    }
}
