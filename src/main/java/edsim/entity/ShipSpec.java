package edsim.entity;

import lombok.*;

@Builder(setterPrefix = "with")
public class ShipSpec
{
    @Getter
    private int optionalInternal;

    @Getter
    private int utility;

    @Getter
    private double shields;

    @Getter
    private double shieldKinetic;

    @Getter
    private double shieldThermal;

    @Getter
    private double shieldExplosive;
}
