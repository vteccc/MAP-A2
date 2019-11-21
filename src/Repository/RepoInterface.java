package Repository;

import Model.MyException;
import Model.ProgramState;

public interface RepoInterface {
    ProgramState getCurrentProgram();
    void logProgramStateExecute() throws MyException;
}
