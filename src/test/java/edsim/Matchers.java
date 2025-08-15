package edsim;

import static org.hamcrest.Matchers.equalTo;
import org.hamcrest.*;
import edsim.entity.*;
import edsim.entity.Module;

public class Matchers
{
    public static Matcher<Effect> equalToEffect(Effect effect)
    {
        return ComposeBuilder.of(Effect.class)
            .withFeature("name", Effect::getName, effect.getName())
            .withFeature("type", Effect::getType, effect.getType())
            .withFeature("kineticResistance", Effect::getKineticResistance, effect.getKineticResistance())
            .withFeature("thermalResistance", Effect::getThermalResistance, effect.getThermalResistance())
            .withFeature("explosiveResistance", Effect::getExplosiveResistance, effect.getExplosiveResistance())
            .withFeature("hullBoost", Effect::getHullBoost, effect.getHullBoost())
            .build();
    }

    public static Matcher<Module> equalToModule(Module.ModuleBuilder moduleBuilder)
    {
        Module module = moduleBuilder.build();

        return ComposeBuilder.of(Module.class)
            .withFeature("type", Module::getType, equalTo(ModuleType.ARMOUR))
            .withFeature("blueprint", Module::getBlueprint, equalToEffect(module.getBlueprint()))
            .withFeature("experimental", Module::getExperimental, equalToEffect(module.getExperimental()))
            .build();
    }
}
