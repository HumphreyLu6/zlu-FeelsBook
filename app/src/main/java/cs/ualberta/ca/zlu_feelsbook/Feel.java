package cs.ualberta.ca.zlu_feelsbook;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public abstract class Feel {
    protected String message;
    protected Date date;
    protected String feel;
    public Feel(String message, String feelNumber){
        if (message.equals("")){
            this.message = "";
        }
        else{
            this.message = message;
        }

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
        String[] splited = text.split(" \\| ");
        this.feel = splited[1];
        this.message = splited[2];
        SimpleDateFormat sdf= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        //sdf.setTimeZone(TimeZone.getTimeZone("MDT"));
        try {
            String tempDateString = splited[0];
            this.date = sdf.parse(tempDateString);
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
