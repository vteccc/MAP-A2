package View;

import Controller.Controller;
import Model.Exp.ArithmeticExp;
import Model.Exp.RelationExp;
import Model.Exp.ValueExp;
import Model.Exp.VarExp;
import Model.ProgramState;
import Model.Statement.*;
import Model.Type.BooleanType;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Repository.RepoInterface;
import Repository.Repository;

class Interpreter {
    public static void main(String[] args) {
        InterfaceStatement ex1;
        ex1 = new CompStatement(new VarDeclarationStatement
                ("v", new IntType()),
                new CompStatement
                        (new AssignStatement
                                ("v", new ValueExp(new IntValue(2))),
                                new PrintStatement(new VarExp("v"))));
        ProgramState p1 = new ProgramState(ex1);
        RepoInterface repo1 = new Repository(p1, "log1.txt");
        Controller contr1 = new Controller(repo1);

        InterfaceStatement ex2;
        ex2 = new CompStatement
                (new VarDeclarationStatement("a", new IntType()),
                        new CompStatement(new VarDeclarationStatement("b", new IntType()),
                                new CompStatement(new AssignStatement("a",
                                        new ArithmeticExp(new ValueExp(new IntValue(2)),
                                                 "+",new ArithmeticExp(new ValueExp(new IntValue(3)), "*",new ValueExp(new IntValue(5))))),
                                        new CompStatement(new AssignStatement("b",
                                                new ArithmeticExp(new VarExp("a"), "+", new ValueExp(new IntValue(1)))),
                                                new PrintStatement(new VarExp("b"))))));
        ProgramState p2 = new ProgramState(ex2);
        RepoInterface repo2 = new Repository(p2, "log2.txt");
        Controller contr2 = new Controller(repo2);

        InterfaceStatement ex3;
        ex3 = new CompStatement
                (new VarDeclarationStatement("a", new BooleanType()),
                        new CompStatement(new VarDeclarationStatement("v", new IntType()),
                                new CompStatement(new AssignStatement("a", new ValueExp(new BoolValue(true))),
                                        new CompStatement(new IfStatement(new VarExp("a"),
                                                new AssignStatement("v", new ValueExp(new IntValue(2))),
                                                new AssignStatement("v", new ValueExp(new IntValue(3)))),
                                                new PrintStatement(new VarExp("v"))))));
        ProgramState p3 = new ProgramState(ex3);
        RepoInterface repo3 = new Repository(p3, "log3.txt");
        Controller contr3 = new Controller(repo3);

        InterfaceStatement ex4;
        ex4 = new CompStatement(new VarDeclarationStatement("varf", new StringType()),
                new CompStatement(
                        new AssignStatement("varf", new ValueExp(new StringValue("/home/cvl/IdeaProjects/lab2/test.in"))),
                        new CompStatement(new OpenReadFile(new VarExp("varf")),
                                new CompStatement(new VarDeclarationStatement("varc", new IntType()),
                                        new CompStatement(new ReadFile(new VarExp("varf"), "varc"),
                                                new CompStatement(new PrintStatement(new VarExp("varc")),
                                                        new CompStatement(new ReadFile(new VarExp("varf"), "varc"),
                                                                new CompStatement(new PrintStatement(new VarExp("varc")), new CloseReadFile(new VarExp("varf"))))))))));
        ProgramState p4 = new ProgramState(ex4);
        RepoInterface repo4 = new Repository(p4, "log4.txt");
        Controller contr4 = new Controller(repo4);

        InterfaceStatement ex5;
        ex5 = new CompStatement(new VarDeclarationStatement
                ("v", new IntType()),
                new CompStatement(new AssignStatement("v", new ValueExp(new IntValue(2))),
                        new CompStatement(new VarDeclarationStatement("w", new IntType()),
                                new CompStatement(new AssignStatement("w", new ValueExp(new IntValue(3))),
                                        new PrintStatement(new RelationExp(new VarExp("v"),">",new VarExp("w")))))));
        ProgramState p5 = new ProgramState(ex5);
        RepoInterface repo5 = new Repository(p5, "log5.txt");
        Controller contr5 = new Controller(repo5);


        TextMenu menu = new TextMenu();
        menu.addCommand(new RunExample("1", "Example 1", contr1));
        menu.addCommand(new RunExample("2", "Example 2", contr2));
        menu.addCommand(new RunExample("3", "Example 3", contr3));
        menu.addCommand(new RunExample("4", "Example 4", contr4));
        menu.addCommand(new RunExample("5", "Example 5", contr5));
        menu.addCommand(new ExitCommand("0","Exit"));
        menu.show();
    }
}
