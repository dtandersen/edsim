package edsim.entity;

import lombok.*;

@Builder(setterPrefix = "with")
public class Effect
{
    @Getter
    private int id;

    @Getter
    @NonNull
    private ModuleType type;

    @Getter
    private String name;

    @Getter
    private double hullBoost;

    @Getter
    private double kineticResistance;

    @Getter
    private double thermalResistance;

    @Getter
    private double explosiveResistance;
}
