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
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping(value="/room", params={"delete"})
    public String delete(Model model, @ModelAttribute Room room) {
        log.info("delete {}", room);
        Long id = room.getId();
        if (id != null) {
            roomRepository.delete(room);
        }
        model.addAttribute("room", new Room());
        model.addAttribute("rooms", roomRepository.findAll());
        return "room :: #roomForm";
    }

    @PostMapping(value="/room", params={"new"})
    public String newRoom(Model model) {
        log.info("new room");
        model.addAttribute("room", new Room());
        model.addAttribute("rooms", roomRepository.findAll());
        model.addAttribute("messages", List.of("Enter a new room."));
        return "room :: #roomForm";
    }

    @PostMapping(value="/room", params={"save"})
    // FOR WHEN WE NEED FLASH: public String saveRoom(Model model, @ModelAttribute Room room, RedirectAttributes redirectAttributes) {
    public String saveRoom(Model model, @ModelAttribute Room room) {
        log.info("Saving {}", room);
        roomRepository.save(room);
        model.addAttribute("rooms", roomRepository.findAll());
        model.addAttribute("messages", List.of("Saved " + room));
        // FOR WHEN WE NEED FLASH:
        //redirectAttributes.addFlashAttribute("messages", List.of("Saved " + room));
        //log.debug("flashMap: {}", redirectAttributes);
        return "room :: #roomForm";
    }

    @PostMapping(value="/select", params={"selectedRoom"})
    public String select(Model model, @RequestParam String selectedRoom) {
        log.info("select {}", selectedRoom);
        Long roomId = Long.valueOf(selectedRoom);
        Room room = roomRepository.findById(roomId).get();
        model.addAttribute("room", room);
        model.addAttribute("rooms", roomRepository.findAll());
        return "room :: #roomForm";
    }

    @GetMapping("/search")
    public String search(Model model) {
        log.info("search {}", model);
        //model.addAttribute("rooms", roomRepository.findAll());
        return "search";
    }
}
