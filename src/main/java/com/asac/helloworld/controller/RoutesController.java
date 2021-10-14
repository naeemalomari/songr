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
        model.addAttribute("song", songRepository.findAll());
        return "songs";
        // HTML PAGE;
    }

    @GetMapping("/albums/{title}")
    public String getOneAlbum(@PathVariable String title, Model model) {
        model.addAttribute("album", albumRepository.findAlbumByTitle(title));
        return "oneAlbum";
        //HTML PAGE;
    }

    @GetMapping("/albums/add/{title}")
    public String viewAdd(@PathVariable String title, Model model) {
        model.addAttribute("album", albumRepository.findAlbumByTitle(title));

        return "addSongs";
        //HTML PAGE;
    }

    //Osaid Alhomedy  helped me with this To make it transactional
    @Transactional
    //Osaid Alhomedy  helped me with this To make it transactional
    @PostMapping("/albums/add/{albumTitle}")
    public RedirectView createNewSongs(@PathVariable String albumTitle, @ModelAttribute Song song) {
        System.out.println("====================================================");
        System.out.println(albumTitle);
        Album album = albumRepository.findAlbumByTitle(albumTitle);
        System.out.println("====================================================");
        System.out.println(album);
        System.out.println("====================================================");
        song.setAlbum(album);
        System.out.println("====================================================");
        album.getSongsList().add(song);
        System.out.println(album.getSongsList());
        System.out.println("====================================================");
        songRepository.save(song);
        albumRepository.save(album);
        return new RedirectView("/albums");
    }
}
