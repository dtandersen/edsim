package edsim.repo;

import edsim.entity.*;

public class Experimentals extends Effects
{
    public Experimentals()
    {
        add(Effect.builder()
            .withName("Angled Plating")
            .withSlot(SlotType.ARMOUR)
            .withType(ModuleType.ARMOUR)
            .withHullBoost(-.03)
            .withKineticResistance(.08));

        add(Effect.builder()
            .withName("Deep Plating")
            .withSlot(SlotType.ARMOUR)
            .withType(ModuleType.ARMOUR)
            .withHullBoost(.08)
            .withKineticResistance(-.03)
            .withThermalResistance(-.03)
            .withExplosiveResistance(-.03));

        add(Effect.builder()
            .withName("Layered Plating")
            .withSlot(SlotType.ARMOUR)
            .withType(ModuleType.ARMOUR)
            .withHullBoost(-.03)
            .withExplosiveResistance(.08));

        add(Effect.builder()
            .withName("Reflective Plating")
            .withSlot(SlotType.ARMOUR)
            .withType(ModuleType.ARMOUR)
            .withHullBoost(-.03)
            .withThermalResistance(.08));

        add(Effect.builder()
            .withName("Blast Block")
            .withSlot(SlotType.UTILITY)
            .withType(ModuleType.SHIELD_BOOSTER)
            .withShieldBoost(-.01)
            .withExplosiveResistance(.02));

        add(Effect.builder()
            .withName("Force Block")
            .withSlot(SlotType.UTILITY)
            .withType(ModuleType.SHIELD_BOOSTER)
            .withShieldBoost(-.01)
            .withKineticResistance(.02));

        add(Effect.builder()
            .withName("Super Capacitors")
            .withSlot(SlotType.UTILITY)
            .withType(ModuleType.SHIELD_BOOSTER)
            .withShieldBoost(.05)
            .withKineticResistance(-.02)
            .withThermalResistance(-.02)
            .withExplosiveResistance(-.02));

        add(Effect.builder()
            .withName("Thermo Block")
            .withSlot(SlotType.UTILITY)
            .withType(ModuleType.SHIELD_BOOSTER)
            .withShieldBoost(-.01)
            .withThermalResistance(.02));
    }
}
