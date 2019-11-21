package View;

import Controller.Controller;
import Model.MyException;

public class RunExample extends Command {
    private Controller ctr;
    public RunExample(String key, String description,Controller ctr){
        super(key,description);
        this.ctr=ctr;
    }
    @Override
    public void execute() {
        try{
            ctr.complete_exe();
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
    }
}
