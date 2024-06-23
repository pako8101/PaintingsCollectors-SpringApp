package com.paintingscollectors.controller;

import com.paintingscollectors.config.LoggedUser;
import com.paintingscollectors.model.dto.PaintingInfoDto;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.enums.StyleName;
import com.paintingscollectors.service.PaintingService;
import com.paintingscollectors.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    private final LoggedUser loggedUser;
    private final UserService userService;
    private final PaintingService paintingService;

    public HomeController(LoggedUser loggedUser, UserService userService, PaintingService paintingService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.paintingService = paintingService;
    }


    @GetMapping("/")
    public String notLogged(){
        if (loggedUser.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    @Transactional
    public String logged(Model model){
        if (!loggedUser.isLoggedIn()) {
            return "redirect:/";
        }

        Map<StyleName, List<Painting>> allPaintings =
                paintingService.findAllByStyle();

        List<PaintingInfoDto> otherPaintings =
                paintingService.findAllPaintings()
                        .stream()
                        .map(PaintingInfoDto::new)
                        .toList();

        List<PaintingInfoDto> myPaintings =
                userService.findAddedPaintings(loggedUser.getId())
                        .stream()
                        .map(PaintingInfoDto::new)
                        .toList();
        List<PaintingInfoDto> favourites =
                userService.findFavourites(loggedUser.getId())
                        .stream()
                        .map(PaintingInfoDto::new)
                        .toList();

model.addAttribute("favourites", favourites);
model.addAttribute("myPaintings", myPaintings);
model.addAttribute("otherPaintings", otherPaintings);












        return "home";
    }
}
