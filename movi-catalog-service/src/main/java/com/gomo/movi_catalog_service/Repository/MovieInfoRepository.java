package com.gomo.movi_catalog_service.Repository;


import com.gomo.movi_catalog_service.Model.MovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieInfoRepository  extends JpaRepository<MovieInfo,Long> {
}
