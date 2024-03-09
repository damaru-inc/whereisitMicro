package com.damaru.whereisit.web;

import com.damaru.whereisit.db.RoomRepository;
import com.damaru.whereisit.model.Room;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.List;
import java.util.Map;

@Controller
public class WebController {

    private Logger log = LoggerFactory.getLogger(WebController.class);

    private final RoomRepository roomRepository;


    public WebController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/room")
    public String rooms(HttpServletRequest request, Model model) {
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        log.debug("flashMap: {}", inputFlashMap);
        if (inputFlashMap != null) {
            List<String> messages = (List<String>) inputFlashMap.get("messages");
            model.addAttribute("messages", messages);
        }

        model.addAttribute("rooms", roomRepository.findAll());
        model.addAttribute("room", new Room());
        log.info("model: {}", model);
        return "room";
    }

    @PostMapping(value="/room", params={"save"})
    public String saveRoom(@ModelAttribute Room room, RedirectAttributes redirectAttributes) {
        log.info("Saving {}", room);
        roomRepository.save(room);
        redirectAttributes.addFlashAttribute("messages", List.of("Saved " + room));
        log.debug("flashMap: {}", redirectAttributes);
        return "redirect:room";
    }

    @PostMapping(value="/room", params={"new"})
    public String newRoom() {
        log.info("new room");
        return "redirect:room";
    }

    @GetMapping("/search")
    public String search(Model model) {
        log.info("search {}", model);
        //model.addAttribute("rooms", roomRepository.findAll());
        return "search";
    }
}
