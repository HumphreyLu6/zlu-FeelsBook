package cs.ualberta.ca.zlu_feelsbook;

import java.util.Date;

public abstract class Feel {
    protected String message;
    protected Date date;
    protected String feel;
    public Feel(String message, String feelNumber){
        this.message = message;
        this.date = new Date();

        if (feelNumber.equals("1")){
            this.feel ="Love";
        }
        else if(feelNumber.equals("2")){
            this.feel="Joy";
        }
        else if(feelNumber.equals("3")){
            this.feel="Surprise";
        }
        else if(feelNumber.equals("4")){
            this.feel="Sadness";
        }
        else if(feelNumber.equals("5")){
            this.feel="Anger";
        }
        else if(feelNumber.equals("6")){
            this.feel="Fear";
        }
        else{
            this.feel=null;
        }
        //System.out.println(feelNumber);
    }

    public String toString(){
        return this.date.toString()+" | "+this.feel + " | "+ this.message;
    }

}
