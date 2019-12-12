package Repository;

import Model.MyException;
import Model.ProgramState;

import java.util.List;

public interface RepoInterface {
    void logProgramStateExecute(ProgramState programState) throws MyException;
    List<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> listProgram);
}
