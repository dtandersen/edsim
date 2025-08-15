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
}
