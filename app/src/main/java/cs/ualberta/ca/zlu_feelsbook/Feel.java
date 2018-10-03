package cs.ualberta.ca.zlu_feelsbook;

import java.util.Date;

public abstract class Feel {
    protected String message;
    protected Date date;
    public Feel(String message){
        this.message = message;
        this.date = new Date();
    }

    public String toString(){
        return this.date.toString()+" | "+ this.message;
    }

}
