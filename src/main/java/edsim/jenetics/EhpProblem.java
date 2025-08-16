package edsim.jenetics;

import java.util.function.*;
import edsim.entity.*;
import io.jenetics.*;
import io.jenetics.engine.*;
import io.jenetics.ext.moea.*;
import io.jenetics.util.*;

public class EhpProblem implements Problem<ShipSim, IntegerGene, Vec<int[]>>
{
    private ShipSpec shipSpec;

    public EhpProblem(ShipSpec shipSpec)
    {
        this.shipSpec = shipSpec;
    }

    @Override
    public Codec<ShipSim, IntegerGene> codec()
    {
        return Codec.of(
            () -> Generator.createIndividual(2 + shipSpec.getUtility() * 2),
            chromosomes -> new ShipSim(ISeq.of(chromosomes)));
    }

    @Override
    public Function<ShipSim, Vec<int[]>> fitness()
    {
        return shipSim -> Vec.of(shipSim.getEhp());
    }

    public static void main(String[] args)
    {
        // final var board = Board.BOARD1;
        final var problem = new EhpProblem(ShipSpec.builder().withUtility(5).build());

        // Crossovers like SinglePoint can be used
        var engine = Engine.builder(problem)
            .optimize(Optimize.MINIMUM)
            .alterers(
                new SwapMutator<>(0.05)
            // new RowCrossover(0.6)
            // new SinglePointCrossover<>(0.3)
            )
            .selector(new TournamentSelector<>(2))
            .populationSize(300)
            .build();

        // final var bestPhenotypes = new ArrayList<Phenotype<IntegerGene,
        // Integer>>();
        final ISeq<Phenotype<IntegerGene, Vec<int[]>>> paretoSet = engine.stream()
            .limit(1000)
            // .peek(r -> bestPhenotypes.add(r.bestPhenotype()))
            // .collect(toBestPhenotype());
            .collect(MOEA.toParetoSet(IntRange.of(1, 5)));
        // System.out.println("Issues: " + best.fitness());
        // final ShipSim grid = problem.decode(paretoSet.);
        // System.out.println(grid);
        for (Phenotype<IntegerGene, Vec<int[]>> phenotype : paretoSet)
        {
            ShipSim shipSim = problem.decode(phenotype.genotype());
            System.out.println(shipSim);
        }
    }
}
