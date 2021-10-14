package com.asac.helloworld.model;


import javax.persistence.*;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    String title;
    Long length;
    int trackNumber;

    public Song(String title, Long length, int trackNumber) {
        this.title = title;
        this.length = length;
        this.trackNumber = trackNumber;
    }

    public Album getAlbum(Album album) {
        return this.album;
    }
    public void setAlbum(Album album) {
        this.album = album;
    }

    public Song( Album album, String title, Long length, int trackNumber) {

        this.album = album;
        this.title = title;
        this.length = length;
        this.trackNumber = trackNumber;
    }

    public Song() {
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Long getLength() {
        return length;
    }
    public void setLength(Long length) {
        this.length = length;
    }
    public int getTrackNumber() {
        return trackNumber;
    }
    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", album=" + album +
                ", title='" + title + '\'' +
                ", length=" + length +
                ", trackNumber=" + trackNumber +
                '}';
    }
}