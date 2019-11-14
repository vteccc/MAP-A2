package Repository;

import Model.ProgramState;

import java.util.ArrayList;
import java.util.List;


public class Repository implements RepoInterface {
    private List<ProgramState> _programStates;

    public Repository() {
        _programStates = new ArrayList<>();
    }

    public void add_program(ProgramState programState) {
        _programStates.add(programState);
    }

    @Override
    public ProgramState getCurrentProgram() {
        return _programStates.get(_programStates.size() - 1);
    }
}
