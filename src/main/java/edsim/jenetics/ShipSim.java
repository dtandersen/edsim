package edsim.jenetics;

import io.jenetics.*;
import io.jenetics.util.*;

public record ShipSim(ISeq<Chromosome<IntegerGene>> chromosomes)
{
    public int getEhp()
    {
        return 0;
    }
}
