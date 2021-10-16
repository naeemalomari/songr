package com.asac.helloworld.repository;

import com.asac.helloworld.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
List<Song> findAllByAlbum_title(String title);
}
