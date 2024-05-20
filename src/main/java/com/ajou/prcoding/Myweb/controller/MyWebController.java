package com.ajou.prcoding.Myweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ajou.prcoding.Myweb.dto.FavoriteMusicRequestDto;
import com.ajou.prcoding.Myweb.dto.MusicList;
import com.ajou.prcoding.Myweb.entity.FavoriteMusic;
import com.ajou.prcoding.Myweb.service.MusicService;


@RestController
public class MyWebController {

    @Autowired
    MusicService service;

    @GetMapping(value = "/musicSearch/{name}")
    public MusicList musicSearchByPath(@PathVariable String name){
        return service.searchMusic(name);
    }

    @GetMapping(value="/musicSearch")
    public MusicList musicSearchByParam(@RequestParam(value="term") String name) {
        return service.searchMusic(name);
    }

    @GetMapping(value="/likes")  //Get Favorite Music list from Database
    public List<FavoriteMusic> getLikes() {
        return service.getLikes();
    }

    @PostMapping(value="/likes")
    public int postLikes(@RequestBody FavoriteMusicRequestDto favorite) {
        return service.saveFavorite(favorite);
    }

    @DeleteMapping(value ="/likes/{id}")
    public int deleteFavorite(@PathVariable String id){
        return service.deleteFavorite(id);
    }
}

