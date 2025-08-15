import io.jenetics.*;
import io.jenetics.util.*;

public class Generator
{
    public static Genotype<IntegerGene> createIndividual()
    {
        MSeq<Chromosome<IntegerGene>> sudokuChromosomes = MSeq.ofLength(Ship.SIZE);

        return Genotype.of(sudokuChromosomes);
    }
}
