package Model.Statement;

import Model.MyException;
import Model.ProgramState;

public interface InterfaceStatement {
    ProgramState execute(ProgramState state) throws MyException;

    String toString();
}
