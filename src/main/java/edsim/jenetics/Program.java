package edsim.jenetics;

import io.jenetics.*;
import io.jenetics.engine.*;
import io.jenetics.util.*;

public class Program
{
    private static int eval(Genotype<SlotGene> gt)
    {
        return gt.chromosome()
            .as(SlotChromosome.class)
            .getEhp();
    }

    public static void main(String[] args)
    {
        // 1.) Define the genotype (factory) suitable
        // for the problem.
        Factory<Genotype<SlotGene>> gtf = Genotype.of(SlotChromosome.of(10, 0.5));

        // 3.) Create the execution environment.
        Engine<SlotGene, Integer> engine = Engine
            .builder(Program::eval, gtf)
            .build();

        // 4.) Start the execution (evolution) and
        // collect the result.
        Genotype<SlotGene> result = engine.stream()
            .limit(100)
            .collect(EvolutionResult.toBestGenotype());

        System.out.println("Hello World:\n" + result);
    }
}
