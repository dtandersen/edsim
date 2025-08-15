package edsim.repo;

import edsim.entity.*;

public class Blueprints extends Effects
{
    public Blueprints()
    {
        add(Effect.builder()
            .withName("Blast Resistant")
            .withSlot(SlotType.ARMOUR)
            .withType(ModuleType.ARMOUR)
            .withKineticResistance(-.12)
            .withThermalResistance(-.12)
            .withExplosiveResistance(.40));

        add(Effect.builder()
            .withName("Heavy Duty")
            .withSlot(SlotType.ARMOUR)
            .withType(ModuleType.ARMOUR)
            .withHullBoost(.32)
            .withKineticResistance(.05)
            .withThermalResistance(.05)
            .withExplosiveResistance(.05));

        add(Effect.builder()
            .withName("Kinetic Resistant")
            .withSlot(SlotType.ARMOUR)
            .withType(ModuleType.ARMOUR)
            .withKineticResistance(.40)
            .withThermalResistance(-.12)
            .withExplosiveResistance(-.12));

        add(Effect.builder()
            .withName("Lightweight")
            .withSlot(SlotType.ARMOUR)
            .withType(ModuleType.ARMOUR)
            .withHullBoost(-.05)
            .withKineticResistance(.15)
            .withThermalResistance(.15)
            .withExplosiveResistance(.15));

        add(Effect.builder()
            .withName("Thermal Resistant")
            .withSlot(SlotType.ARMOUR)
            .withType(ModuleType.ARMOUR)
            .withKineticResistance(-.12)
            .withThermalResistance(.40)
            .withExplosiveResistance(-.12));

        add(Effect.builder()
            .withName("Heavy Duty")
            .withSlot(SlotType.UTILITY)
            .withType(ModuleType.SHIELD_BOOSTER)
            .withShieldBoost(.38));

        add(Effect.builder()
            .withName("Kinetic Resistant")
            .withSlot(SlotType.UTILITY)
            .withType(ModuleType.SHIELD_BOOSTER)
            .withKineticResistance(.07)
            .withThermalResistance(-.01)
            .withExplosiveResistance(-.01));

        add(Effect.builder()
            .withName("Resistance Augmented")
            .withSlot(SlotType.UTILITY)
            .withType(ModuleType.SHIELD_BOOSTER)
            .withKineticResistance(.17)
            .withThermalResistance(.17)
            .withExplosiveResistance(.17));

        add(Effect.builder()
            .withName("Thermal Resistant")
            .withSlot(SlotType.UTILITY)
            .withType(ModuleType.SHIELD_BOOSTER)
            .withKineticResistance(-.04)
            .withThermalResistance(.27)
            .withExplosiveResistance(-.04));
    }
}
