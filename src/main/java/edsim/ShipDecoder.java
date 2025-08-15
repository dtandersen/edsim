package edsim;

import java.util.*;
import edsim.entity.*;
import edsim.entity.Module;
import edsim.repo.*;

public class ShipDecoder
{
    private Blueprints blueprints;

    private Experimentals experimentals;

    public ShipDecoder(Blueprints blueprints, Experimentals experimentals)
    {
        this.blueprints = blueprints;
        this.experimentals = experimentals;
    }

    public Ship decode(ShipSpec spec, List<Integer> data)
    {
        Ship ship = new Ship();
        ship.hull = Module.builder()
            .withType(ModuleType.ARMOUR)
            .withBlueprint(blueprints.get(data.get(0)))
            .withExperimental(experimentals.get(data.get(0)))
            .build();
        return ship;
    }
}
