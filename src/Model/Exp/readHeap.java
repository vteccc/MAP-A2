package Model.Exp;

import Model.ADT.MyInterfaceDictionary;
import Model.MyException;
import Model.Value.ReferenceValue;
import Model.Value.Value;

public class readHeap implements Exp {
    private Exp exp;

    public readHeap(Exp exp){
        this.exp=exp;
    }
    @Override
    public Value evaluate(MyInterfaceDictionary<String, Value> table, MyInterfaceDictionary<Integer, Value> heapTable) throws MyException {
        if(exp.evaluate(table,heapTable).getType().toString().contains("Reference(")){
            ReferenceValue referenceValue= (ReferenceValue) exp.evaluate(table,heapTable);
            if(heapTable.isDefined(referenceValue.getAddress())){
                return heapTable.getValue(referenceValue.getAddress());
            }else{
                throw new MyException("Error:Reference Value is not defined in the heap table!");
            }
        }
        else{
            throw new MyException("Error:Expression must be of Reference Type!");
        }
    }
    @Override
    public String toString(){
        return exp.toString();
    }
}
