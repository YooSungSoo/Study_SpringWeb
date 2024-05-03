package com.ajou.prcoding.Myweb.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.ajou.prcoding.Myweb.dto.MusicList;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyWebController {
    
    RestTemplate restTemplate = new RestTemplate(); 

    @GetMapping(value="/musicSearch/{term}")
    public MusicList musicSearchByPath(@PathVariable String term) {

        try {
            String response = restTemplate.getForObject("https://itunes.apple.com/search?term="+ term +"&entity=album ", String.class);
            ObjectMapper mapper = new ObjectMapper();
            MusicList list = mapper.readValue(response, MusicList.class);
            System.out.println(list.getResultCount());
            return list;
          } catch(IOException e) {
            System.out.println(e.toString());
            return null;
          }
        
     }
    
    @GetMapping(value="/musicSearch")
    public MusicList musicSearchByParam(@RequestParam String term){

        try {
            String response = restTemplate.getForObject("https://itunes.apple.com/search?term="+term+"&entity=album ", String.class);
            ObjectMapper mapper = new ObjectMapper();
            MusicList list = mapper.readValue(response, MusicList.class);
            System.out.println(list.getResultCount());
            return list;
          } catch(IOException e) {
            System.out.println(e.toString());
            return null;
          }
        
    }
}