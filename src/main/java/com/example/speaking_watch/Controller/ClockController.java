package com.example.speaking_watch.Controller;

import com.example.speaking_watch.Service.ClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping("/api")
@Controller
public class ClockController {

    @Autowired
    private ClockService clockService;

    @GetMapping("/convert")
    public ResponseEntity<String> gettimewords(){
        try {
            String result = clockService.convertTimeToWords();
            return ResponseEntity.ok(result);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
        }
    }

    @GetMapping("/time")
    public ResponseEntity<String> convertTime(@RequestParam("time") String time){
        try{
            String result = clockService.convertTime(time);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
        }
    }
}
