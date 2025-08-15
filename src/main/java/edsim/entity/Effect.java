package edsim.entity;

import lombok.*;

@Builder(setterPrefix = "with")
@ToString
public class Effect
{
    @Getter
    private int id;

    @Getter
    @NonNull
    private SlotType slot;

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

    @Getter
    private double shieldBoost;

    public boolean hasSlot(SlotType slotType)
    {
        return this.slot == slotType;
    }

    public boolean hasType(ModuleType type)
    {
        return this.type == type;
    }
}
