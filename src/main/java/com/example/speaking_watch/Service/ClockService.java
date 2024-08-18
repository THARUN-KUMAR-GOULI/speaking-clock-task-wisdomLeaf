package com.example.speaking_watch.Service;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import com.example.speaking_watch.Exception.ExceptionHandler;

@Service
public class ClockService {

    public String convertTimeToWords(){
        LocalTime time = LocalTime.now();
        return getTimeToWords(time.format(DateTimeFormatter.ofPattern("HH:mm")));
    }

    public String getTimeToWords(String time){
        String[] timeparts = time.split(":");

        if(timeparts.length != 2){
            throw new ExceptionHandler("Invalid time format, please use 'HH:mm' format");
        }

        int hours = Integer.parseInt(timeparts[0]);
        int min = Integer.parseInt(timeparts[1]);

        String[] hourWords = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
                "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty",
                "TwentyOne", "TwentyTwo", "TwentyThree", "TwentyFour"};

        String[] minuteWords = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
                "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty",
                "TwentyOne", "TwentyTwo", "TwentyThree", "TwentyFour", "TwentyFive", "TwentySix", "TwentySeven",
                "TwentyEight", "TwentyNine", "Thirty", "ThirtyOne", "ThirtyTwo", "ThirtyThree", "ThirtyFour",
                "ThirtyFive", "ThirtySix", "ThirtySeven", "ThirtyEight", "ThirtyNine", "Forty", "FortyOne",
                "FortyTwo", "FortyThree", "FortyFour", "FortyFive", "FortySix", "FortySeven", "FortyEight",
                "FortyNine", "Fifty", "FiftyOne", "FiftyTwo", "FiftyThree", "FiftyFour", "FiftyFive", "FiftySix",
                "FiftySeven", "FiftyEight", "FiftyNine"};

        return hourWords[hours] + "hours" + minuteWords[min] + "minutes";
    }

    public String convertTime(String time){
        if(time.matches("\\d{2}:\\d{2}")){
            if ("12:00".equals(time)) {
                return "It's Midday";
            } else if ("00:00".equals(time)) {
                return "It's Midnight";
            } else {
                return getTimeToWords(time);
            }
        } return "Invalid format";
    }
}
