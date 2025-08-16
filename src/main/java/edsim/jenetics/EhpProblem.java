package edsim.jenetics;

import java.util.function.*;
import edsim.entity.*;
import edsim.repo.*;
import io.jenetics.*;
import io.jenetics.engine.*;
import io.jenetics.ext.moea.*;
import io.jenetics.util.*;

public class EhpProblem implements Problem<ShipSim, IntegerGene, Vec<double[]>>
{
    private ShipSpec shipSpec;

    private Generator generator;

    public EhpProblem(ShipSpec shipSpec)
    {
        this.shipSpec = shipSpec;
        this.generator = new Generator(new Blueprints(), new Experimentals());
    }

    @Override
    public Codec<ShipSim, IntegerGene> codec()
    {
        return Codec.of(
            () -> generator.createIndividual(1 + shipSpec.getUtility()),
            chromosomes -> new ShipSim(shipSpec, ISeq.of(chromosomes)));
    }

    @Override
    public Function<ShipSim, Vec<double[]>> fitness()
    {
        return shipSim -> Vec.of(
            shipSim.getTotalShieldKineticEhp(),
            shipSim.getTotalShieldThermalEhp(),
            shipSim.getTotalShieldExplosiveEhp() * .75);
    }

    public static void main(String[] args)
    {
        // final var board = Board.BOARD1;
        ShipSpec shipSpec = ShipSpec.builder()
            .withUtility(5)
            .withShields(349.9)
            .withShieldKinetic(.4)
            .withShieldThermal(-.2)
            .withShieldExplosive(.5)
            .build();
        final var problem = new EhpProblem(shipSpec);

        // Crossovers like SinglePoint can be used
        var engine = Engine.builder(problem)
            .optimize(Optimize.MAXIMUM)
            .alterers(
                new SwapMutator<>(0.05),
                // new RowCrossover(0.6)
                // new Mutator<>(0.05),
                new SinglePointCrossover<>(0.3))
            // .selector(new TournamentSelector<>(2))
            .offspringSelector(new TournamentSelector<>(4))
            // .survivorsSelector(UFTournamentSelector.ofVec())
            .survivorsSelector(NSGA2Selector.ofVec())
            .populationSize(300)
            .build();

        // final var bestPhenotypes = new ArrayList<Phenotype<IntegerGene,
        // Integer>>();
        final ISeq<Phenotype<IntegerGene, Vec<double[]>>> paretoSet = engine.stream()
            .limit(25000)
            // .peek(r -> bestPhenotypes.add(r.bestPhenotype()))
            // .collect(toBestPhenotype());
            .collect(MOEA.toParetoSet(IntRange.of(3, 10)));
        // System.out.println("Issues: " + best.fitness());
        // final ShipSim grid = problem.decode(paretoSet.);
        // System.out.println(grid);
        for (Phenotype<IntegerGene, Vec<double[]>> phenotype : paretoSet)
        {
            ShipSim shipSim = problem.decode(phenotype.genotype());
            System.out.println(shipSim);
        }
    }
}
