package com.damaru.whereisit.web;

import com.damaru.whereisit.db.RoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private final RoomRepository roomRepository;


    public WebController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/room")
    public String rooms(Model model) {
        model.addAttribute("rooms", roomRepository.findAll());
        return "room";
    }


    @GetMapping("/search")
    public String search(Model model) {
        //model.addAttribute("rooms", roomRepository.findAll());
        return "search";
    }


}
