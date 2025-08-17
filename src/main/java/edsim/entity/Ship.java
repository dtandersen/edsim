package edsim.entity;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import lombok.*;

@Builder(setterPrefix = "with")
@ToString
public class Ship
{
    @Getter
    // @NonNull
    private double baseArmour;

    @Getter
    // @NonNull
    private double bulkheadHullBoost;

    @Getter
    // @NonNull
    private double shields;

    @Getter
    private Module bulkhead;

    @Getter
    private double baseShieldKineticResist;

    @Getter
    private double baseShieldThermalResist;

    @Getter
    private double baseShieldExplosiveResist;

    @Getter
    private List<Module> utilities;

    // public double getTotalShields()
    // {
    // return utilities.stream()
    // .mapToDouble(m ->
    // Optional.of(m.getBlueprint()).map(Effect::getShieldBoost).orElse(0.0))
    // .sum();
    // }

    public double getTotalArmour()
    {
        double blueprintHullboost = getTotalBlueprintEffect(Effect::getHullBoost);
        double experimentalHullboost = getTotalExperimentalEffect(Effect::getHullBoost);

        return baseArmour * (1 + bulkheadHullBoost) * (1 + blueprintHullboost) * (1 + experimentalHullboost);
    }

    public double getTotalShields()
    {
        Stream<Module> boosters = utilities.stream()
            .filter(m -> m.hasType(ModuleType.SHIELD_BOOSTER));

        Stream<Double> mods = boosters.map(this::getShieldBoost);
        double totalBoosterMod = mods.reduce(0.0, (subtotal, element) -> subtotal + element);

        return shields * (1 + totalBoosterMod);
    }

    /**
     * FinalShield = InitalShield x 1.04OE booster x 1.38G5 Heavy Duty x
     * 1.05Super Capacitors (MJ)
     *
     * @param booster
     * @return
     */
    private double getShieldBoost(Module booster)
    {
        return (1 + .20) * (1 + Optional.ofNullable(booster.getBlueprint()).map(Effect::getShieldBoost).orElse(0.0)) *
            (1 + Optional.ofNullable(booster.getExperimental()).map(Effect::getShieldBoost).orElse(0.0)) - 1;
    }

    private double getShieldBoosterEffect(Module module, Function<Effect, Double> mapper)
    {
        double blueprint = Optional.ofNullable(module.getBlueprint()).map(mapper).orElse(0.0);
        double experimental = Optional.ofNullable(module.getExperimental()).map(mapper).orElse(0.0);

        double val = (1 - blueprint) * (1 - experimental);
        return val;
    }

    protected double getTotalBlueprintEffect(ToDoubleFunction<Effect> mapper)
    {
        double blueprintHullboost = getBlueprints()
            .filter(Objects::nonNull)
            .mapToDouble(mapper)
            .sum();

        return blueprintHullboost;
    }

    protected double getTotalExperimentalEffect(ToDoubleFunction<Effect> mapper)
    {
        double blueprintHullboost = getExperimentals()
            .filter(Objects::nonNull)
            .mapToDouble(mapper)
            .sum();

        return blueprintHullboost;
    }

    protected Stream<Effect> getBlueprints()
    {
        return Stream.concat(Stream.of(bulkhead != null ? bulkhead.getBlueprint() : null),
            utilities.stream().map(Module::getBlueprint))
            .filter(Objects::nonNull);
    }

    protected Stream<Effect> getExperimentals()
    {
        return Stream.concat(Stream.of(bulkhead != null ? bulkhead.getExperimental() : null),
            utilities.stream().map(Module::getExperimental))
            .filter(Objects::nonNull);
    }

    public double getTotalShieldKineticResist()
    {
        Stream<Module> boosters = utilities.stream()
            .filter(m -> m.hasType(ModuleType.SHIELD_BOOSTER));

        Stream<Double> mods = boosters.map(module -> getShieldBoosterEffect(module, Effect::getKineticResistance));
        double shieldBoosterMod = mods.reduce(1.0, (subtotal, element) -> subtotal * element);

        double res = getEffectiveDamageResistance(0.0, 1 - shieldBoosterMod, baseShieldKineticResist, 0);

        return res;
    }

    /**
     * https://www.reddit.com/r/EliteDangerous/comments/6nf1yj/can_someone_kindly_explain_how_shield_boosters/dk9cdgc/
     *
     * @return
     */
    public double getTotalShieldThermalResist()
    {
        Stream<Module> boosters = utilities.stream()
            .filter(m -> m.hasType(ModuleType.SHIELD_BOOSTER));

        Stream<Double> mods = boosters.map(module -> getShieldBoosterEffect(module, Effect::getThermalResistance));
        double shieldBoosterMod = mods.reduce(1.0, (subtotal, element) -> subtotal * element);

        double res = getEffectiveDamageResistance(0.0, 1 - shieldBoosterMod, baseShieldThermalResist, 0);

        return res;
    }

    private double getEffectiveDamageResistance(double baseres, double extrares, double exemptres, double bestres)
    {
        // https://forums.frontier.co.uk/threads/kinetic-resistance-calculation.266235/post-4230114
        // https://forums.frontier.co.uk/threads/shield-booster-mod-calculator.286097/post-4998592
        // https://forums.frontier.co.uk/threads/this-is-how-resistance-stacking-works.439830/
        /*
         * old
         * var threshold = 30;
         * var rawres = 1 - ((1 - baseres / 100) * (1 - extrares / 100));
         * var maxres = 1 - ((1 - baseres / 100) * (1 - threshold / 100));
         * return 100 * (rawres - max(0, pow((rawres - maxres) / 2, curve ||
         * 1)));
         */
        // baseres = baseres || 0;
        // extrares = extrares || 0;
        // exemptres = exemptres || 0;
        // bestres = bestres || 0;
        double lo = Math.max(Math.max(.30, baseres), bestres);
        double hi = .65;
        double expected = (1 - ((1 - baseres) * (1 - extrares)));
        double penalized = lo + (expected - lo) / (1 - lo) * (hi - lo);
        double actual = ((penalized >= .30) ? penalized : expected);
        return (1 - ((1 - exemptres) * (1 - actual)));
    }

    public double getTotalShieldExplosiveResist()
    {
        Stream<Module> boosters = utilities.stream()
            .filter(m -> m.hasType(ModuleType.SHIELD_BOOSTER));

        Stream<Double> mods = boosters.map(module -> getShieldBoosterEffect(module, Effect::getExplosiveResistance));
        double shieldBoosterMod = mods.reduce(1.0, (subtotal, element) -> subtotal * element);

        double res = getEffectiveDamageResistance(0.0, 1 - shieldBoosterMod, baseShieldExplosiveResist, 0);

        return res;
    }

    public double getTotalShieldKineticEhp()
    {
        double ehp = getTotalShields() / (1.0 - getTotalShieldKineticResist());
        return ehp;
    }

    public double getTotalShieldThermalEhp()
    {
        double ehp = getTotalShields() / (1.0 - getTotalShieldThermalResist());
        return ehp;
    }

    public double getTotalShieldExplosiveEhp()
    {
        double ehp = getTotalShields() / (1.0 - getTotalShieldExplosiveResist());
        return ehp;
    }
}
