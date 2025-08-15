package edsim;

import java.util.*;
import edsim.entity.*;
import edsim.entity.Module;
import edsim.entity.Ship.*;
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
        ShipBuilder shipBuilder = Ship.builder();
        Effect blueprint = blueprints.findById(data.get(0));
        Effect experimental = experimentals.findById(data.get(1));

        shipBuilder.withHull(Module.builder()
            .withType(ModuleType.ARMOUR)
            .withBlueprint(blueprint.getType() == ModuleType.ARMOUR ? blueprint : null)
            .withExperimental(experimental.getType() == ModuleType.ARMOUR ? experimental : null)
            .build());

        int index = 2;

        blueprint = blueprints.findById(data.get(2));
        experimental = experimentals.findById(data.get(3));

        if (blueprint.getSlot() != SlotType.OPTIONAL_INTERNAL)
        {
            blueprint = null;
        }

        if (experimental.getSlot() != SlotType.OPTIONAL_INTERNAL)
        {
            experimental = null;
        }

        if (blueprint != null || experimental != null)
        {
            shipBuilder.withOptionalInternals(List.of(
                Module.builder()
                    .withType(ModuleType.SHIELD_BOOSTER)
                    .withBlueprint(blueprint)
                    .withExperimental(experimental)
                    .build()));
        }
        else
        {
            shipBuilder.withOptionalInternals(Collections.emptyList());
        }

        return shipBuilder.build();
    }
}
