package edsim.jenetics;

public class Program
{
    // private static int eval(Genotype<IntegerGene> gt)
    // {
    // return gt.chromosome()
    // .as(IntegerChromosome.class)
    // .getEhp();
    // }

    // public static void main(String[] args)
    // {
    // // 1.) Define the genotype (factory) suitable
    // // for the problem.
    // Factory<Genotype<IntegerGene>> gtf = Genotype.of(IntegerGene.of(0, 0,
    // 999));

    // // 3.) Create the execution environment.
    // Engine<IntegerGene, Integer> engine = Engine
    // .builder(Program::eval, gtf)
    // .build();

    // // 4.) Start the execution (evolution) and
    // // collect the result.
    // Genotype<IntegerGene> result = engine.stream()
    // .limit(100)
    // .collect(EvolutionResult.toBestGenotype());

    // System.out.println("Hello World:\n" + result);
    // }
}
