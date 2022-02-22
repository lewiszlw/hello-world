using Xunit;
using MyStruct = hello_world_csharp.ProgramStructure.MyNestedNamespace.MyStruct;

namespace hello_world_csharp_tests.ProgramStructure;

public class MyNamespaceTest
{
    [Fact]
    public void Test1()
    {
        new MyStruct();
    }
}