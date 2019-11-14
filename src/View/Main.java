package View;

import Controller.Controller;
import Model.Exp.ArithmeticExp;
import Model.Exp.ValueExp;
import Model.Exp.VarExp;
import Model.MyException;
import Model.ProgramState;
import Model.Statement.*;
import Model.Type.BooleanType;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Repository.Repository;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws MyException {
        System.out.println("1 - Run example 1,\n2 - Run example 2,\n3 - Run example 3.");
        Scanner read_from_keyboard = new Scanner(System.in);
        System.out.println("Insert option: ");
        boolean ok = true;
        int option = read_from_keyboard.nextInt();
        //1
        if (option == 1) {
            Repository repository = new Repository();
            Controller controller = new Controller(repository);
            System.out.println("Example 1:\n");
            InterfaceStatement ex1;
            ex1 = new CompStatement(new VarDeclarationStatement
                    ("v", new IntType()),
                    new CompStatement
                            (new AssignStatement
                                    ("v", new ValueExp(new IntValue(2))),
                                    new PrintStatement(new VarExp("v"))));
            ProgramState p1 = new ProgramState();
            p1.getStack().push(ex1);
            repository.add_program(p1);
            while (ok) {

                System.out.println("1 - One step,\n2 - Full execution,\n3 - Display state,\n0 - Exit.");
                System.out.println("Insert option: ");
                option = read_from_keyboard.nextInt();
                if (option == 0)
                    ok = false;
                if (option == 1) {
                    try {
                        System.out.println(controller.one_step());
                    } catch (MyException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                if (option == 2) {
                    try {
                        controller.complete_exe();
                    } catch (MyException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                if (option == 3) {
                    System.out.println(controller.display_state());
                }
            }
        }
        //2
        if (option == 2) {
            Repository repository = new Repository();
            Controller controller = new Controller(repository);
            System.out.println("Example 2:\n");
            InterfaceStatement ex2;
            ex2 = new CompStatement
                    (new VarDeclarationStatement("a", new IntType()),
                            new CompStatement(new VarDeclarationStatement("b", new IntType()),
                                    new CompStatement(new AssignStatement("a",
                                            new ArithmeticExp(new ValueExp(new IntValue(2)),
                                                    new ArithmeticExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), 3),
                                                    1)),
                                            new CompStatement(new AssignStatement("b",
                                                    new ArithmeticExp(new VarExp("a"), new ValueExp(new IntValue(1)), 1)),
                                                    new PrintStatement(new VarExp("b"))))));
            ProgramState p2 = new ProgramState();
            p2.getStack().push(ex2);
            repository.add_program(p2);
            while (ok) {
                System.out.println("1 - One step,\n2 - Full execution,\n3 - Display state.");
                System.out.println("Insert option: ");
                option = read_from_keyboard.nextInt();
                if (option == 0)
                    ok = false;
                if (option == 1) {
                    try {
                        System.out.println(controller.one_step());
                    } catch (MyException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                if (option == 2) {
                    try {
                        controller.complete_exe();
                    } catch (MyException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                if (option == 3) {
                    System.out.println(controller.display_state());
                }
            }
        }
        //3
        if (option == 3) {
            Repository repository = new Repository();
            Controller controller = new Controller(repository);
            System.out.println("Example 3:\n");
            InterfaceStatement ex3 = new CompStatement
                    (new VarDeclarationStatement("a", new BooleanType()),
                            new CompStatement(new VarDeclarationStatement("v", new IntType()),
                                    new CompStatement(new AssignStatement("a", new ValueExp(new BoolValue(true))),
                                            new CompStatement(new IfStatement(new VarExp("a"),
                                                    new AssignStatement("v", new ValueExp(new IntValue(2))),
                                                    new AssignStatement("v", new ValueExp(new IntValue(3)))),
                                                    new PrintStatement(new VarExp("v"))))));
            ProgramState p3 = new ProgramState();
            p3.getStack().push(ex3);
            repository.add_program(p3);
            while (ok) {
                System.out.println("1 - One step,\n2 - Full execution,\n3 - Display state.");
                System.out.println("Insert option: ");
                option = read_from_keyboard.nextInt();
                if (option == 0)
                    ok = false;
                if (option == 1) {
                    try {
                        System.out.println(controller.one_step());
                    } catch (MyException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                if (option == 2) {
                    try {
                        controller.complete_exe();
                    } catch (MyException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                if (option == 3) {
                    System.out.println(controller.display_state());
                }
            }
        }
    }
}
