package com.paintingscollectors.repository;

import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaintingRepository extends JpaRepository<Painting,Long> {

    Optional<Painting> findByIdAndOwner(Long id, User owner);

    List<Painting> findAllByStyle(Style style);

    List<Painting> findAllByName(String name);
}
