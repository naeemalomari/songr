package com.asac.helloworld.repository;


import com.asac.helloworld.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
            Album findAlbumByTitle(String title);
}
