package com.ajou.prcoding.Myweb.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ajou.prcoding.Myweb.entity.FavoriteMusic;

public interface FavoriteRepository extends 	JpaRepository<FavoriteMusic, String>
 {
    List<FavoriteMusic> findAll();
    void deleteById(String id);
}
