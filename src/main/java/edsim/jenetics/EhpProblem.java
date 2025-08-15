package edsim.jenetics;

import static io.jenetics.engine.EvolutionResult.toBestPhenotype;
import java.util.*;
import java.util.function.*;
import io.jenetics.*;
import io.jenetics.engine.*;
import io.jenetics.util.*;

public class EhpProblem implements Problem<ShipSim, IntegerGene, Integer>
{
    @Override
    public Codec<ShipSim, IntegerGene> codec()
    {
        return Codec.of(
            () -> Generator.createIndividual(),
            chromosomes -> new ShipSim(ISeq.of(chromosomes)));
    }

    @Override
    public Function<ShipSim, Integer> fitness()
    {
        return ShipSim::getEhp;
    }

    public static void main(String[] args)
    {
        // final var board = Board.BOARD1;
        final var problem = new EhpProblem();

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

        final var bestPhenotypes = new ArrayList<Phenotype<IntegerGene, Integer>>();
        final var best = engine.stream()
            .limit(1000)
            .peek(r -> bestPhenotypes.add(r.bestPhenotype()))
            .collect(toBestPhenotype());
        System.out.println("Issues: " + best.fitness());
        final ShipSim grid = problem.decode(best.genotype());
        System.out.println(grid);
    }
}
