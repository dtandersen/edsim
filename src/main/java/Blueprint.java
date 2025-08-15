import lombok.*;

@Builder(setterPrefix = "with")
public class Blueprint
{
    @Getter
    String name;

    @Getter
    double hullBoost;

    @Getter
    double kineticResistance;

    @Getter
    double thermalResistance;

    @Getter
    double explosiveResistance;
}
