package edsim.jenetics;

import io.jenetics.*;
import io.jenetics.util.*;

public class Generator
{
    public static Genotype<IntegerGene> createIndividual(int size)
    {
        MSeq<Chromosome<IntegerGene>> sudokuChromosomes = MSeq.ofLength(size);
        for (int i = 0; i < size; i++)
        {
            MSeq<IntegerGene> genes = MSeq.ofLength(1);
            for (int j = 0; j < 1; j++)
            {
                genes.set(j, IntegerGene.of(IntRange.of(0, 100)));
            }
            sudokuChromosomes.set(i, IntegerChromosome.of(genes));
        }

        return Genotype.of(sudokuChromosomes);
    }
}
