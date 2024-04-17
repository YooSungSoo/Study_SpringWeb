package com.ajou.prcoding.Myweb.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.ajou.prcoding.Myweb.dto.*;
import com.ajou.prcoding.Myweb.entity.FavoriteMusic;
import com.ajou.prcoding.Myweb.repository.FavoriteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@RestController 
public class MyWebController {

  @Autowired
  FavoriteRepository albumsRepo;
  
    RestTemplate restTemplate = new RestTemplate(); 
    
    @GetMapping(value="/musicSearch/{term}")
    public  MusicList musicSearchByPath(@PathVariable String term) {

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
    public MusicList musicSearchByParam(@RequestParam String term) {
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

    @GetMapping(value="/likes") 
    public List<FavoriteMusic> getLikes() {

        try {
            return albumsRepo.findAll();

        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
}
    @PostMapping(value="/likes")
    @Transactional
    public int postLikes(@RequestBody FavoriteMusicRequestDto favorite) {
    FavoriteMusic music = albumsRepo.save(favorite.toEntity());
    if(music != null) {
        return 1;
    }
    else {
        return 0;
    }
}


}

