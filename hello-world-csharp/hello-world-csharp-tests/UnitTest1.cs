using hello_world_csharp.ProgramStructure;
using Xunit;

namespace hello_world_csharp_tests;

public class UnitTest1
{
    [Fact]
    public void Test1()
    {
        Assert.Equal(120, Functions.Factorial(5));
    }
}