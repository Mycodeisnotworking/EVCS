package com.kw.evcs.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kw.evcs.domain.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

}
