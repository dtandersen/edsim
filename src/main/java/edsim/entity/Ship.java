package edsim.entity;

import java.util.*;
import lombok.*;

@Builder(setterPrefix = "with")
@ToString
public class Ship
{
    @Getter
    public Module hull;

    @Getter
    public List<Module> utilities;

    public double getShields()
    {
        return utilities.stream()
            .mapToDouble(m -> Optional.of(m.getBlueprint()).map(Effect::getShieldBoost).orElse(0.0))
            .sum();
    }
}
