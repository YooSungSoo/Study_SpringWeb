package com.ajou.prcoding.Myweb.dto;

import com.ajou.prcoding.Myweb.entity.FavoriteMusic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FavoriteMusicRequestDto {
    private String collectionId;
    private String collectionViewUrl;
    public FavoriteMusic toEntity() {
        FavoriteMusic music = new FavoriteMusic();
        music.setCollectionId(this.collectionId);
        return music;
    }
}
