package com.paintingscollectors.controller;

import com.paintingscollectors.config.LoggedUser;
import com.paintingscollectors.model.dto.AddPaintingDto;
import com.paintingscollectors.service.PaintingService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PaintingController {
    private final PaintingService paintingService;
    private final LoggedUser loggedUser;


    public PaintingController(PaintingService paintingService, LoggedUser loggedUser) {
        this.paintingService = paintingService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute
    public AddPaintingDto addPaintingDto() {
        return new AddPaintingDto();
    }

    @GetMapping("/paintings")
    public String viewAddPaint() {
        if (!loggedUser.isLoggedIn()) {
            return "redirect:/login";
        }
        return "add-painting";
    }

    @PostMapping("/paintings")
    public String addPaint(
            @Valid AddPaintingDto paintingData,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!loggedUser.isLoggedIn()) {
            return "redirect:/login";
        } else {
            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("addPaintingDto", paintingData);
                redirectAttributes.addFlashAttribute("org.springframework" +
                                ".validation.BindingResult" +
                                ".addPaintingDto",
                        bindingResult);
                return "redirect:/paintings";

            }
            paintingService.add(paintingData);

            return "redirect:/";
        }
    }

    @PostMapping("/add-to-favourites/{paintingId}")
    public String addToFavourites(@PathVariable long paintingId) {

        if (!loggedUser.isLoggedIn()) {
            return "redirect:/";
        }

        paintingService.addToFavorites(loggedUser.getId(), paintingId);


        return "redirect:/home";


    }

    @DeleteMapping("/paintings/{id}")

    public String deletePaint(@PathVariable Long id) {
        paintingService.delete(id);

        return "redirect:/home";
    }

    @GetMapping("/paintings/vote/{paintingId}")
    public String vote(@PathVariable long paintingId) {

paintingService.mostVotedPaint(paintingId);
        return "redirect:/home";
    }


}
