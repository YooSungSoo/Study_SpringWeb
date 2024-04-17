package com.ajou.prcoding.Myweb.controller;

import java.io.IOException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.ajou.prcoding.Myweb.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController 
public class MyWebController {

    RestTemplate restTemplate = new RestTemplate(); 
    
    @GetMapping(value="/musicSearch/{term}")
    public String musicSearchByPath(@PathVariable String term) {

        try {
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            MusicList list = mapper.readValue(response, MusicList.class);
            System.out.println(list.getResultCount());
            return list;
          } catch(IOException e) {
            System.out.println(e.toString());
          }
    
 }
    @GetMapping(value="/musicSearch")
    public String musicSearchByParam(@RequestParam String term) {

        try {
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            MusicList list = mapper.readValue(response, MusicList.class);
            System.out.println(list.getResultCount());
            return list;
          } catch(IOException e) {
            System.out.println(e.toString());
          }
         }

}

