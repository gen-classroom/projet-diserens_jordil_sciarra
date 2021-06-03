package ch.heigvd.statique.benchmarks;


import ch.heigvd.statique.Statique;
import org.openjdk.jmh.annotations.*;
import picocli.CommandLine;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class Benchmarks
{
    String path = "./monSite";

    @Setup
    public void setup()
    {
        new CommandLine(new Statique()).execute("init", path);
    }

    @Benchmark
    public void benchmark()
    {
        new CommandLine(new Statique()).execute("build", path);
    }

}
