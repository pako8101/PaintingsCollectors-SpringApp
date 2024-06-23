package com.paintingscollectors.service.impl;

import com.paintingscollectors.config.LoggedUser;
import com.paintingscollectors.model.dto.AddPaintingDto;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.model.entity.enums.StyleName;
import com.paintingscollectors.repository.PaintingRepository;
import com.paintingscollectors.repository.StyleRepository;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.service.PaintingService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaintingServiceImpl implements PaintingService {
    private final UserRepository userRepository;
    private final PaintingRepository paintingRepository;
    private final StyleRepository styleRepository;
    private final LoggedUser loggedUser;

    public PaintingServiceImpl(UserRepository userRepository, PaintingRepository paintingRepository, StyleRepository styleRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.paintingRepository = paintingRepository;
        this.styleRepository = styleRepository;
        this.loggedUser = loggedUser;
    }

    @Override
    public boolean add(AddPaintingDto paintingData) {
        if (!loggedUser.isLoggedIn()){
            return false;
        }

        Optional<User> userById = userRepository.
                findById(loggedUser.getId());
        if (userById.isEmpty()){
            return false;
        }
        Optional<Style> styleName = styleRepository
                .findByStyleName(paintingData.getStyle());
        if (styleName.isEmpty()){
            return false;
        }
        Painting painting = new Painting();
        painting.setName(paintingData.getName())
                .setAuthor(paintingData.getAuthor())
                .setStyle(styleName.get())
                .setImgUrl(paintingData.getImageUrl())
                .setOwner(userById.get());

        List<Painting>userPaintings = userById.get().getAddedPaintings();
        userPaintings.add(painting);
        userById.get().setAddedPaintings(userPaintings);

        paintingRepository.save(painting);



        return true;
    }

    @Override
    public Map<StyleName, List<Painting>> findAllByStyle() {
        Map<StyleName, List<Painting>> result = new HashMap<>();

        List<Style> allStyles =  styleRepository.findAll();

        allStyles.forEach(style -> {
            List<Painting> paintings =
                    paintingRepository.findAllByStyle(style);
            result.put(style.getStyleName(),paintings);
        });



        return result;
    }

    @Override
    public void addToFavorites(long id, long paintId) {
        Optional<User>userById = userRepository.findById(id);
        if (userById.isEmpty()){
            return;
        }
        Optional<Painting> optionalPainting = paintingRepository.findById(paintId);
        if (optionalPainting.isEmpty()){
            return;
        }
        userById.get().addFavourite(optionalPainting.get());

        userRepository.save(userById.get());

    }

    @Override
    public void delete(Long id) {
        userRepository.findById(loggedUser.getId())
                .flatMap(user ->
                        paintingRepository.findByIdAndOwner(id, user))
                .ifPresent(paintingRepository::delete);
    }

    @Override
    public List<Painting> findAllPaintings() {

        return paintingRepository.findAll();
    }

    @Override
    public Optional<Painting> mostVotedPaint(long paintingId) {



        return Optional.empty();
    }
}
