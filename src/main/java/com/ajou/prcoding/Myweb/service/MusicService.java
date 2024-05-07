package com.ajou.prcoding.Myweb.service;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.ajou.prcoding.Myweb.dto.FavoriteMusicRequestDto;
import com.ajou.prcoding.Myweb.dto.MusicList;
import com.ajou.prcoding.Myweb.entity.FavoriteMusic;
import com.ajou.prcoding.Myweb.repository.FavoriteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MusicService {
    private final FavoriteRepository albumsRepo;
    RestTemplate restTemplate = new RestTemplate();

    String url = "https://itunes.apple.com/search?term=aespa&entity=album ";
    
    @GetMapping(value ="/musicSearch")
	public MusicList searchMusic(@RequestParam(value="term") String name){
        RestTemplate  restTemplate =  new RestTemplate();
        String url1= url.replace("aespa", name);
        try{
            String response = restTemplate.getForObject(url1, String.class);
            ObjectMapper mapper = new ObjectMapper();
            MusicList list = mapper.readValue(response, MusicList.class);
            System.out.println(list.getResultCount());
            return list;
        }
        catch(IOException e){
            System.out.println(e.toString());
        }
        return null;
    }

    @GetMapping(value="/likes")
    public List<FavoriteMusic> getLikes(){
        try{
            return albumsRepo.findAll();
        }
        catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    @PostMapping(value ="/likes")
    public int saveFavorite(@RequestBody FavoriteMusicRequestDto favorite){
        FavoriteMusic music = albumsRepo.save(favorite.toEntity());
        if(music != null){
            return 1;            
        }
        else{
            return 0;
        }
    }

    @PostMapping(value = "/likes/{id}")
    public int deleteFavorite(String id){
        FavoriteMusic music = albumsRepo.findById(id).orElse(null);
        if(music != null){
            System.out.println(id + "를 삭제했습니다");
            return 0;            
        }else{
            return 1;
        }
    }

}