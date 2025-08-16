package edsim.jenetics;

import java.util.*;
import edsim.entity.*;
import edsim.repo.*;
import io.jenetics.*;
import io.jenetics.util.*;

public class Generator
{
    private Blueprints s_blueprints;

    private Experimentals s_experimentals;

    private int max;

    private Random random;

    public Generator(Blueprints blueprints, Experimentals experimentals)
    {
        s_blueprints = blueprints;
        s_experimentals = experimentals;
        max = Math.max(s_blueprints.size() - 1, s_experimentals.size()) - 1;
        random = new Random();
    }

    public Genotype<IntegerGene> createIndividual(int size)
    {
        MSeq<Chromosome<IntegerGene>> chromosomes = MSeq.ofLength(size);
        for (int i = 0; i < size; i++)
        {
            MSeq<IntegerGene> genes = MSeq.ofLength(2);
            Effect blueprint = randomBlueprint();
            genes.set(0, IntegerGene.of(blueprint.getId(), IntRange.of(0, max)));

            Effect experimental = randomExperimental(blueprint.getType());
            genes.set(1, IntegerGene.of(experimental.getId(), IntRange.of(0, max)));

            chromosomes.set(i, IntegerChromosome.of(genes));
        }

        return Genotype.of(chromosomes);
    }

    private Effect randomExperimental(ModuleType moduleType)
    {
        List<Effect> effects = s_experimentals.findAllByModule(moduleType);
        if (effects.isEmpty())
        {
            return null;
        }
        return effects.get(random.nextInt(effects.size()));
    }

    protected Effect randomBlueprint()
    {
        return s_blueprints.findById(random.nextInt(s_blueprints.size()));
    }
}
