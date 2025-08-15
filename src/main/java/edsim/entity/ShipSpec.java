package edsim.entity;

import lombok.*;

@Builder(setterPrefix = "with")
public class ShipSpec
{
    @Getter
    private int optionalInternal;

    @Getter
    private int utility;
}
