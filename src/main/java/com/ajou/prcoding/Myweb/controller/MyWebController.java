package com.ajou.prcoding.Myweb.controller;
import java.util.List;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.ajou.prcoding.Myweb.dto.FavoriteMusicRequestDto;
import com.ajou.prcoding.Myweb.dto.MusicList;
import com.ajou.prcoding.Myweb.entity.FavoriteMusic;
import com.ajou.prcoding.Myweb.repository.FavoriteRepository;
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
    @Autowired
    FavoriteRepository albumsRepo;

    //Get Favorite Music list from Database
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