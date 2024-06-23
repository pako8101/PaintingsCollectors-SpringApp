package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.AddPaintingDto;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.enums.StyleName;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PaintingService {

    boolean add(AddPaintingDto paintingData);

    public Map<StyleName, List<Painting>> findAllByStyle();

    void addToFavorites(long id,long paintId);

   public void delete(Long id);

    List<Painting> findAllPaintings();

    Optional<Painting> mostVotedPaint(long paintingId);
}
