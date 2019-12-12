package Repository;

import Model.MyException;
import Model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Repository implements RepoInterface {
    private List<ProgramState> _programStates;
    private String logFilePath;

    public Repository(ProgramState program,String path) {
        _programStates = new ArrayList<>();
        logFilePath=path;
        _programStates.add(program);
    }

    @Override
    public void logProgramStateExecute(ProgramState programState) throws MyException {

        try {
            PrintWriter logFile= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath,true)));
            logFile.print("\nList of programs by indexes:\n [ ");
            for(ProgramState p : _programStates){
                logFile.print(p.getId()+" ");
            }
            logFile.print("]\n");
            logFile.print(programState.toString());
            logFile.close();
        } catch (IOException e) {
            throw new MyException("Error while writing to file!");
        }
    }

    @Override
    public List<ProgramState> getProgramList() {
        return _programStates;
    }

    @Override
    public void setProgramList(List<ProgramState> listProgram) {
        _programStates=listProgram;
    }
}
