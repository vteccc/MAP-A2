package Model.Statement;

import Model.ProgramState;

public class NopStatement {
    private ProgramState old;

    public NopStatement(ProgramState old) {
        this.old = old;
    }

    public ProgramState execute(ProgramState state) {
        return null;
    }

    @Override
    public String toString() {
        return "No operation";
    }
}
