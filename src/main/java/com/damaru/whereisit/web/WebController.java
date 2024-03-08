package com.damaru.whereisit.web;

import com.damaru.whereisit.db.RoomRepository;
import com.damaru.whereisit.model.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    private Logger log = LoggerFactory.getLogger(WebController.class);

    private final RoomRepository roomRepository;


    public WebController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/room")
    public String rooms(Model model) {
        model.addAttribute("rooms", roomRepository.findAll());
        model.addAttribute("room", new Room());
        log.info("model: {}", model);
        return "room";
    }

    @PostMapping("/room")
    public String saveRoom(@ModelAttribute Room room, Model model) {
        log.info("Saving {}", room);
        roomRepository.save(room);
        return "redirect:room";
    }

    @GetMapping("/search")
    public String search(Model model) {
        log.info("search {}", model);
        //model.addAttribute("rooms", roomRepository.findAll());
        return "search";
    }
}
