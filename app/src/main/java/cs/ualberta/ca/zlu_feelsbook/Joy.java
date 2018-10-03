package cs.ualberta.ca.zlu_feelsbook;

public class Joy extends Feel {

    public String feel;
    public Joy(String message){
        super(message);
        this.feel="Joy";
    }
    @Override
    public String toString(){
        return this.date.toString()+ "|"+ this.feel+ "|"+ this.message;
    }
}
