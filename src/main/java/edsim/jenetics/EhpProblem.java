package edsim.jenetics;

import java.util.*;
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
            shipSim.getTotalShieldThermalEhp()
        // -shipSim.getTotalShieldExplosiveEhp()
        // Math.min(shipSim.getTotalShieldKineticEhp(),
        // shipSim.getTotalShieldThermalEhp())
        // Math.min(shipSim.getTotalShieldKineticEhp(),
        // Math.min(shipSim.getTotalShieldThermalEhp(),
        // shipSim.getTotalShieldExplosiveEhp()))
        // shipSim.getTotalShields()
        );
    }

    public static void main(String[] args)
    {
        // final var board = Board.BOARD1;
        ShipSpec shipSpec = ShipSpec.builder()
            .withUtility(5)
            .withShields(349.9)
            .withShieldKinetic(26.9 / 100.0)
            .withShieldThermal(39.1 / 100.0)
            .withShieldExplosive(49.3 / 100.0)
            .build();
        final var problem = new EhpProblem(shipSpec);

        // Crossovers like SinglePoint can be used
        var engine = Engine.builder(problem)
            // .optimize(Optimize.MAXIMUM)
            .alterers(
                new SwapMutator<>(0.03),
                // new RowCrossover(0.6)
                new Mutator<>(0.03),
                new SinglePointCrossover<>(0.3)
            // new MeanAlterer<>()
            )
            // .selector(new TournamentSelector<>(2))
            .offspringSelector(new TournamentSelector<>(5))
            .survivorsSelector(UFTournamentSelector.ofVec())
            // .survivorsSelector(NSGA2Selector.ofVec())
            .maximizing()
            .populationSize(500)
            .build();

        // final var bestPhenotypes = new ArrayList<Phenotype<IntegerGene,
        // Integer>>();
        final ISeq<Phenotype<IntegerGene, Vec<double[]>>> paretoSet = engine.stream()
            .limit(25000)
            // .peek(r -> bestPhenotypes.add(r.bestPhenotype()))
            // .collect(toBestPhenotype());
            .collect(MOEA.toParetoSet(IntRange.of(10, 100)));
        // System.out.println("Issues: " + best.fitness());
        // final ShipSim grid = problem.decode(paretoSet.);
        // System.out.println(grid);
        ArrayList<ShipSim> ships = new ArrayList<>();
        for (Phenotype<IntegerGene, Vec<double[]>> phenotype : paretoSet)
        {
            ShipSim shipSim = problem.decode(phenotype.genotype());
            ships.add(shipSim);
            // System.out.println(shipSim);
        }

        ships.sort(Comparator.comparingDouble(ShipSim::minEhp));
        // ships.sort(Comparator.reverseOrder());

        // Print the sorted ships
        for (ShipSim ship : ships)
        {
            System.out.println(ship);
        }
    }
}
