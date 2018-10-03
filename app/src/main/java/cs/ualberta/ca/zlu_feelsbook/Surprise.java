package cs.ualberta.ca.zlu_feelsbook;

public class Surprise extends Feel {

    public String feel;
    public Surprise(String message){
        super(message);
        this.feel="Surprise";
    }
    @Override
    public String toString(){
        return this.date.toString()+ "|"+ this.feel+ "|"+ this.message;
    }
}
