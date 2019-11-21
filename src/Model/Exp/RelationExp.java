package Model.Exp;

import Model.ADT.MyInterfaceDictionary;
import Model.MyException;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;
import java.util.Set;

public class RelationExp implements Exp {
    private Exp exp1;
    private Exp exp2;
    private String operand;
    private Set<String> operators;

    public RelationExp(Exp exp1, String operand ,Exp exp2) {
        operators = Set.of("<","<=", "==", "!=", ">",">=");
        this.exp1 = exp1;
        this.operand=operand;
        this.exp2 = exp2;
    }

    @Override
    public Value evaluate(MyInterfaceDictionary<String, Value> table) throws MyException {
        if(operators.contains(operand)){
            Value v1, v2;
            v1 = exp1.evaluate(table);
            if (v1.getType().equals(new IntType())) {
                v2 = exp2.evaluate(table);
                if (v2.getType().equals(new IntType())) {
                    IntValue i1 = (IntValue) v1;
                    IntValue i2 = (IntValue) v2;
                    int n1, n2;
                    n1 = i1.getVal();
                    n2 = i2.getVal();
                    if(operand.equals("<")){
                        if(n1<n2){
                            return new BoolValue(true);
                        }
                        else
                            return new BoolValue(false);
                    }
                    if(operand.equals("<=")){
                        if(n1<=n2){
                            return new BoolValue(true);
                        }
                        else
                            return new BoolValue(false);
                    }
                    if(operand.equals("==")){
                        if(n1==n2){
                            return new BoolValue(true);
                        }
                        else
                            return new BoolValue(false);
                    }
                    if(operand.equals("!=")){
                        if(n1!=n2){
                            return new BoolValue(true);
                        }
                        else
                            return new BoolValue(false);
                    }
                    if(operand.equals(">")){
                        if(n1>n2){
                            return new BoolValue(true);
                        }
                        else
                            return new BoolValue(false);
                    }
                    if(operand.equals(">=")){
                        if(n1>n2){
                            return new BoolValue(true);
                        }
                        else
                            return new BoolValue(false);
                    }
                } else
                    throw new MyException("Error:Second operand is not an integer!");
            } else
                throw new MyException("Error:First operand is not an integer!");
        }
        else{
            throw new MyException("Error: Invalid operand!");
        }
        return new BoolValue(false);
    }

    @Override
    public String toString(){
        return exp1.toString()+operand+exp2.toString();
    }
}
