package cs.ualberta.ca.zlu_feelsbook;

public class Love extends Feel {

    public String feel;
    public Love(String message){
        super(message);
        this.feel="Love";
    }
    @Override
    public String toString(){
        return this.date.toString()+ "|"+ this.feel+ "|"+ this.message;
    }
}
