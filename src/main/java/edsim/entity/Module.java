package edsim.entity;

import lombok.*;

@Builder(setterPrefix = "with")
@ToString
public class Module
{
    @Getter
    @NonNull
    private ModuleType type;

    @Getter
    private Effect blueprint;

    @Getter
    private Effect experimental;

    public boolean hasType(ModuleType type)
    {
        return this.type == type;
    }
}
