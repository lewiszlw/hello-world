namespace hello_world_csharp.ProgramStructure;

using System;

public class MainClass
{
    public static void Main(string[] args)
    {
        Console.WriteLine("请输入阶乘n");
        var input = Console.ReadLine();
        if (input == null)
        {
            Console.WriteLine("未输入阶乘n");
            return;
        }
        var result = Functions.Factorial(int.Parse(input));
        Console.WriteLine($"{input} 的阶乘是 {result}");
    }
}

public static class Functions
{
    // 阶乘
    public static long Factorial(int n)
    {
        if (n < 0 || n > 20)
        {
            return -1;
        }

        long result = 1;
        for (int i = 1; i <= n; i++)
        {
            result *= i;
        }

        return result;
    }
}