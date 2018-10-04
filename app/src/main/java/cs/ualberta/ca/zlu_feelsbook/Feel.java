package cs.ualberta.ca.zlu_feelsbook;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

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

    public void modifyFeel(String text){
        String[] splitted = text.split("|");
        this.feel = splitted[1];
        this.message = splitted[2];
        SimpleDateFormat sdf= new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy ");
        try {
            this.date = sdf.parse(splitted[0]);
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        toString();
    }

    public String toString(){
        return this.date.toString()+" | "+this.feel + " | "+ this.message;
    }

}
