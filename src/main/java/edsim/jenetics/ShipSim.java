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

    private ISeq<Chromosome<IntegerGene>> chromosomes;

    private Ship ship;

    public ShipSim(ShipSpec shipSpec, ISeq<Chromosome<IntegerGene>> chromosomes)
    {
        this.shipSpec = shipSpec;
        this.chromosomes = chromosomes;
        this.ship = decode(chromosomes);
    }

    public Ship decode(ISeq<Chromosome<IntegerGene>> chromosomes)
    {
        List<Integer> data = chromosomes.stream()
            .flatMap(chromosome -> chromosome.stream())
            .map(IntegerGene::intValue)
            .toList();

        ShipDecoder decoder = new ShipDecoder(new Blueprints(), new Experimentals());
        Ship ship = decoder.decode(shipSpec, data);

        return ship;
    }

    public int getEhp()
    {
        return (int)ship.getTotalShield();
    }

    public String toString()
    {
        List<Integer> data = chromosomes.stream()
            .flatMap(chromosome -> chromosome.stream())
            .map(IntegerGene::intValue)
            .toList();
        ShipDecoder decoder = new ShipDecoder(new Blueprints(), new Experimentals());
        Ship ship = decoder.decode(shipSpec, data);

        StringBuilder sb = new StringBuilder();
        Module hull = ship.getBulkhead();
        sb.append(data + "\n");
        sb.append("Kinetic   EHP: " + ship.getTotalShieldKineticEhp() + " Resist: " + ship.getTotalShieldKineticResist() * 100 + "%\n");
        sb.append("Thermal   EHP: " + ship.getTotalShieldThermalEhp() + " Resist: " + ship.getTotalShieldThermalResist() * 100 + "%\n");
        sb.append("Explosive EHP: " + ship.getTotalShieldExplosiveEhp() + " Resist: " + ship.getTotalShieldExplosiveResist() * 100 + "%\n");
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
        return (int)ship.getTotalShieldKineticEhp();
    }

    public double getTotalShieldThermalEhp()
    {
        return (int)ship.getTotalShieldThermalEhp();
    }

    public double getTotalShieldExplosiveEhp()
    {
        return (int)ship.getTotalShieldExplosiveEhp();
    }
}
