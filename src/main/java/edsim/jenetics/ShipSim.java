package edsim.jenetics;

import java.util.*;
import edsim.*;
import edsim.entity.*;
import edsim.entity.Module;
import edsim.repo.*;
import io.jenetics.*;
import io.jenetics.util.*;

public class ShipSim
{
    private ShipSpec shipSpec;

    // private ISeq<Chromosome<IntegerGene>> chromosomes;

    private Ship ship;

    public ShipSim(ShipSpec shipSpec, ISeq<Chromosome<IntegerGene>> chromosomes)
    {
        this.shipSpec = shipSpec;
        // this.chromosomes = chromosomes;
        this.ship = decode(chromosomes);
    }

    private Ship decode(ISeq<Chromosome<IntegerGene>> chromosomes)
    {
        List<Integer> data = chromosomes.stream()
            .flatMap(chromosome -> chromosome.stream())
            .map(IntegerGene::intValue)
            .toList();

        ShipDecoder decoder = new ShipDecoder(new Blueprints(), new Experimentals());
        Ship ship = decoder.decode(shipSpec, data);

        return ship;
    }

    public String toString()
    {
        // List<Integer> data = chromosomes.stream()
        // .flatMap(chromosome -> chromosome.stream())
        // .map(IntegerGene::intValue)
        // .toList();
        // ShipDecoder decoder = new ShipDecoder(new Blueprints(), new
        // Experimentals());
        // Ship ship = decoder.decode(shipSpec, data);

        StringBuilder sb = new StringBuilder();
        Module hull = ship.getBulkhead();
        String output = """
            Genes:     %9$s
            Armour:    %1$.1f
            Shields:   %2$.1f
            Kinetic    Resist: %3$6.1f%%  EHP: %6$.0f
            Thermal    Resist: %4$6.1f%%  EHP: %7$.0f
            Explosive  Resist: %5$6.1f%%  EHP: %8$.0f
            """;
        sb.append(String.format(output,
            ship.getTotalArmour(),
            ship.getTotalShields(),
            ship.getTotalShieldKineticResist() * 100,
            ship.getTotalShieldThermalResist() * 100,
            ship.getTotalShieldExplosiveResist() * 100,
            ship.getTotalShieldKineticEhp(),
            ship.getTotalShieldThermalEhp(),
            ship.getTotalShieldExplosiveEhp(),
            new ArrayList<>()));
        // sb.append(data + "\n");
        // sb.append("Armour: " + ship.getTotalArmour() + " Shields: " +
        // ship.getTotalShields() + "\n");
        // sb.append("Kinetic EHP: " + ship.getTotalShieldKineticEhp() + "
        // Resist: " + ship.getTotalShieldKineticResist() * 100 + "%\n");
        // sb.append("Thermal EHP: " + ship.getTotalShieldThermalEhp() + "
        // Resist: " + ship.getTotalShieldThermalResist() * 100 + "%\n");
        // sb.append("Explosive EHP: " + ship.getTotalShieldExplosiveEhp() + "
        // Resist: " + ship.getTotalShieldExplosiveResist() * 100 + "%\n");
        if (hull != null)
        {
            sb.append("Armour: " + moduleToString(hull) + "\n");
        }
        else
        {
            sb.append("Armour: -\n");
        }

        for (Module m : ship.getUtilities())
        {
            sb.append("Utility: " + moduleToString(m) + "\n");
        }

        return sb.toString();
    }

    public String moduleToString(Module module)
    {
        StringBuilder sb = new StringBuilder();
        String bp = null;
        String exp = null;
        if (module.getBlueprint() != null)
        {
            bp = module.getBlueprint().getName();
        }
        if (module.getExperimental() != null)
        {
            exp = module.getExperimental().getName();
        }
        if (bp != null && exp != null)
        {
            sb.append(bp + " / " + exp);
        }
        else if (bp != null)
        {
            sb.append(bp);
        }
        else
        {
            sb.append("-");
        }

        return sb.toString();
    }

    public double getTotalShieldKineticEhp()
    {
        return ship.getTotalShieldKineticEhp();
    }

    public double getTotalShieldThermalEhp()
    {
        return ship.getTotalShieldThermalEhp();
    }

    public double getTotalShieldExplosiveEhp()
    {
        return ship.getTotalShieldExplosiveEhp();
    }

    public double getTotalShields()
    {
        return ship.getTotalShields();
    }

    public double averageEhp()
    {
        return (ship.getTotalShieldKineticEhp() + ship.getTotalShieldThermalEhp() + ship.getTotalShieldExplosiveEhp()) / 3.0;
    }

    public double minEhp()
    {
        return Math.min(ship.getTotalShieldKineticEhp(), Math.min(ship.getTotalShieldThermalEhp(), ship.getTotalShieldExplosiveEhp()));
    }
}
