package cs.ualberta.ca.zlu_feelsbook;

public class Sadness extends Feel {

    public String feel;
    public Sadness(String message){
        super(message);
        this.feel="Sadness";
    }
    @Override
    public String toString(){
        return this.date.toString()+ "|"+ this.feel+ "|"+ this.message;
    }
}
