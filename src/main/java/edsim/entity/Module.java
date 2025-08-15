package edsim.entity;

import lombok.*;

@Builder(setterPrefix = "with")
@ToString
public class Module
{
    @Getter
    private ModuleType type;

    @Getter
    private Effect blueprint;

    @Getter
    private Effect experimental;
}
