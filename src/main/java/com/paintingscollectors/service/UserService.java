package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.UserLoginDto;
import com.paintingscollectors.model.dto.UserRegisterDto;
import com.paintingscollectors.model.entity.Painting;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface UserService {
   boolean register(UserRegisterDto data);
   public boolean login(UserLoginDto data);
   public Set<Painting> findFavourites(long id);

   List<Painting> findAddedPaintings(long id);


}
