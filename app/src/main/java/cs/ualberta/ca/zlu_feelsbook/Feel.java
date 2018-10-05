package cs.ualberta.ca.zlu_feelsbook;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public abstract class Feel implements Comparable<Feel>{
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
        if (splited.length>2){
            this.message = splited[2];
        }
        else{
            this.message = "";
        }
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
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
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); // Quoted "Z" to indicate UTC, no timezone offset
        String iso = df.format(this.date);
        return iso+" | "+this.feel + " | "+ this.message;
    }

    @Override
    public int compareTo(Feel f){
        if (date.compareTo(f.date)>0 ) {
            return -1;
        }
        else if (date.compareTo(f.date)<0) {
            return 1;
        }
        else {
            return 0;
        }
    }

}
