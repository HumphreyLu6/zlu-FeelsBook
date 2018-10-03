package cs.ualberta.ca.zlu_feelsbook;

public class Anger extends Feel {

    public String feel;
    public Anger(String message){
        super(message);
        this.feel="Anger";
    }
    @Override
    public String toString(){
        return this.date.toString()+ "|"+ this.feel+ "|"+ this.message;
    }
}
