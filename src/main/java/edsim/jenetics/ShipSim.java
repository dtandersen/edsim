package edsim.jenetics;

import java.util.*;
import edsim.*;
import edsim.entity.*;
import edsim.entity.Module;
import edsim.repo.*;
import io.jenetics.*;
import io.jenetics.util.*;

public record ShipSim(ISeq<Chromosome<IntegerGene>> chromosomes)
{
    public int getEhp()
    {
        List<Integer> data = chromosomes.stream()
            .flatMap(chromosome -> chromosome.stream())
            .map(IntegerGene::intValue)
            .toList();

        ShipDecoder decoder = new ShipDecoder(new Blueprints(), new Experimentals());
        Ship ship = decoder.decode(ShipSpec.builder().withUtility(5).build(), data);
        return (int)ship.getTotalShield();
    }

    public String toString()
    {
        List<Integer> data = chromosomes.stream()
            .flatMap(chromosome -> chromosome.stream())
            .map(IntegerGene::intValue)
            .toList();
        ShipDecoder decoder = new ShipDecoder(new Blueprints(), new Experimentals());
        Ship ship = decoder.decode(ShipSpec.builder().withUtility(5).build(), data);

        StringBuilder sb = new StringBuilder();
        Module hull = ship.getBulkhead();
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
        List<Integer> data = chromosomes.stream()
            .flatMap(chromosome -> chromosome.stream())
            .map(IntegerGene::intValue)
            .toList();

        ShipDecoder decoder = new ShipDecoder(new Blueprints(), new Experimentals());
        Ship ship = decoder.decode(ShipSpec.builder().withUtility(5).build(), data);
        return (int)ship.getTotalShieldKineticEhp();
    }

    public double getTotalShieldThermalEhp()
    {
        List<Integer> data = chromosomes.stream()
            .flatMap(chromosome -> chromosome.stream())
            .map(IntegerGene::intValue)
            .toList();

        ShipDecoder decoder = new ShipDecoder(new Blueprints(), new Experimentals());
        Ship ship = decoder.decode(ShipSpec.builder().withUtility(5).build(), data);
        return (int)ship.getTotalShieldThermalEhp();
    }

    public double getTotalShieldExplosiveEhp()
    {
        List<Integer> data = chromosomes.stream()
            .flatMap(chromosome -> chromosome.stream())
            .map(IntegerGene::intValue)
            .toList();

        ShipDecoder decoder = new ShipDecoder(new Blueprints(), new Experimentals());
        Ship ship = decoder.decode(ShipSpec.builder().withUtility(5).build(), data);
        return (int)ship.getTotalShieldExplosiveEhp();
    }
}
