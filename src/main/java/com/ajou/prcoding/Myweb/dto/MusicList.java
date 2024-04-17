package com.ajou.prcoding.Myweb.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MusicList {
    private Integer resultCount;
    private List<Map<String, Object>> results;

}
