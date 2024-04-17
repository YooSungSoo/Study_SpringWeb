package com.ajou.prcoding.Myweb.service;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.ajou.prcoding.Myweb.dto.FavoriteMusicRequestDto;
import com.ajou.prcoding.Myweb.dto.MusicList;
import com.ajou.prcoding.Myweb.entity.FavoriteMusic;
import com.ajou.prcoding.Myweb.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MusicService {
    private final FavoriteRepository albumsRepo;
    RestTemplate restTemplate = new RestTemplate();

    private final FavoriteRepository favoriteRepository;

    public MusicList searchMusic(String name) {
        String url = "itunes.apple.com/search?term="+ name +"&entity=album" ;
        return restTemplate.getForObject(url, MusicList.class);
    }

    public List<FavoriteMusic> getLikes() {
        return favoriteRepository.findAll();
    }

    public int saveFavorite(FavoriteMusicRequestDto favorite) {
        FavoriteMusic music = favorite.toEntity();
        FavoriteMusic savedMusic = favoriteRepository.save(music);
        return (savedMusic != null) ? 1 : 0; 
    }

    public void deleteFavorite(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException();
        }
        favoriteRepository.deleteById(id);
    }
}
    