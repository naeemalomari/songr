package com.asac.helloworld.controller;

import com.asac.helloworld.model.Album;
import com.asac.helloworld.model.Song;
import com.asac.helloworld.repository.AlbumRepository;
import com.asac.helloworld.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.transaction.Transactional;
import java.util.List;


@Controller
public class RoutesController {

    private final AlbumRepository albumRepository;
    @Autowired
    private SongRepository songRepository;
    // there is no difference between these two repository
//    @Autowired
//    private AlbumRepository albumRepository;

    public RoutesController(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", required = false, defaultValue = "world") String name,
                        Model model) {
        model.addAttribute("name", name); // this is passed to the template automatically
        return "hello"; // this represents the name of the html template which will be rendered
    }

    @GetMapping("/capitalize/{name}")
//    @ResponseBody
    public String capitalize(@PathVariable String name, Model model) {
        model.addAttribute("name", name.toUpperCase());
        return "capitalize";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homepage(Model model) {
        model.addAttribute("message", "Welcome to our application");
        return "homepage";
    }

    @GetMapping("/albums")
    public String getAlbum(Model model) {
        model.addAttribute("albums", albumRepository.findAll());
        return "albums";
    }

    @PostMapping("/albums")
    public RedirectView createNewAlbumPost(@ModelAttribute Album album) {
        albumRepository.save(album);
        return new RedirectView("albums");
    }
///////////////////////lab13//////////////////////////

    @GetMapping("/songs")
    public String getSongs(Model model) {
        model.addAttribute("songs", songRepository.findAll());
        return "songs";
        // HTML PAGE;
    }

    @GetMapping("/albums/{title}")
    public String getOneAlbum(@PathVariable String title, Model model) {
        Album currentAlbum = albumRepository.findAlbumByTitle(title);
        currentAlbum.setSongsList(songRepository.findAllByalbum_id(currentAlbum.getId()));
        model.addAttribute("albums", currentAlbum);
        return "oneAlbum";
        //HTML PAGE;
    }

    @GetMapping("/albums/add/{title}")
    public String viewAdd(@PathVariable String title, Model model) {
        model.addAttribute("album", albumRepository.findAlbumByTitle(title));
        return "addSongs";
        //HTML PAGE;
    }

    @GetMapping("/AddSong")
    public RedirectView getSongs(@PathVariable Long id, @ModelAttribute Model model) {

        model.addAttribute("Song", songRepository.findAllByalbum_id(id));
        return new RedirectView("/songs");

    }

    @Transactional
    //Osaid Alhomedy  helped me with this To make it transactional
    @PostMapping("/albums/add/{title}")
    public RedirectView createNewSongs(@PathVariable String title, @ModelAttribute Song song) {
        Album album = albumRepository.findAlbumByTitle(title);
        song.setAlbum(album);
        album.getSongsList().add(song);
        songRepository.save(song);
        albumRepository.save(album);
        return new RedirectView("/songs");
    }
}