package com.kw.evcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kw.evcs.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

}
