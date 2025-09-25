package com.gomo.movi_catalog_service.Controller;


import com.gomo.movi_catalog_service.Model.MovieInfo;
import com.gomo.movi_catalog_service.Repository.MovieInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieInfoController {


    @Autowired
    private MovieInfoRepository movieInfoRepository;

    @PostMapping("/movie-info/save")
    public List<MovieInfo> saveAll(@RequestBody List<MovieInfo> moviInfoList){
        return movieInfoRepository.saveAll(moviInfoList);
    }

    @GetMapping("/movie-info/list")
    public List<MovieInfo> getAll(){
        return movieInfoRepository.findAll();
    }
}
