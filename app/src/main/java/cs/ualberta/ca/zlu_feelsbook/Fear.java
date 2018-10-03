package cs.ualberta.ca.zlu_feelsbook;

public class Fear extends Feel {

    public String feel;
    public Fear(String message){
        super(message);
        this.feel="Fear";
    }
    @Override
    public String toString(){
        return this.date.toString()+ "|"+ this.feel+ "|"+ this.message;
    }
}
