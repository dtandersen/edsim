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
        ShipBuilder shipBuilder = Ship.builder()
            // .withBaseArmour(spec.getBaseArmour())
            // .withBulkheadHullBoost(spec.getBulkheadHullBoost())
            .withShields(spec.getShields())
            .withBaseShieldKineticResist(spec.getShieldKinetic())
            .withBaseShieldThermalResist(spec.getShieldThermal())
            .withBaseShieldExplosiveResist(spec.getShieldExplosive());

        Module hullModule = loadModule(SlotType.ARMOUR, data.get(0), data.get(1));
        if (hullModule != null)
        {
            shipBuilder.withBulkhead(hullModule);
        }
        else
        {
            shipBuilder.withBulkhead(Module.builder()
                .withType(ModuleType.BULKHEAD)
                .withBlueprint(null)
                .withExperimental(null)
                .build());
        }

        List<Module> utilities = new ArrayList<>();
        for (int i = 0; i < spec.getUtility(); i++)
        {
            int blueprintId = data.get(2 + i * 2);
            int experimentalId = data.get(2 + i * 2 + 1);
            Module utility = loadModule(SlotType.UTILITY, blueprintId, experimentalId);

            if (utility != null)
            {
                utilities.add(utility);
            }
        }
        shipBuilder.withUtilities(utilities);

        return shipBuilder.build();
    }

    private Module loadModule(SlotType slotType, int blueprintId, int experimentalId)
    {
        Effect blueprint = blueprints.findById(blueprintId);
        if (blueprint == null || blueprint.getSlot() != slotType)
        {
            return null;
        }

        Effect experimental = experimentals.findById(experimentalId);
        if (experimental != null && (!experimental.hasSlot(slotType) || !experimental.hasType(blueprint.getType())))
        {
            experimental = null;
        }

        return Module.builder()
            .withType(blueprint.getType())
            .withBlueprint(blueprint)
            .withExperimental(experimental)
            .build();
    }
}
