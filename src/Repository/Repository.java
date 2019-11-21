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

    public void add_program(ProgramState programState) {
        _programStates.add(programState);
    }

    @Override
    public ProgramState getCurrentProgram() {
        return _programStates.get(_programStates.size() - 1);
    }

    @Override
    public void logProgramStateExecute() throws MyException {

        try {
            PrintWriter logFile= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath,true)));
            logFile.print(getCurrentProgram().toString());
            logFile.close();
        } catch (IOException e) {
            throw new MyException("Error while writing to file!");
        }
    }
}
