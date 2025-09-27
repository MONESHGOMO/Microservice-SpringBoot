package com.gomo.movi_catalog_service.Controller;


import com.gomo.movi_catalog_service.Model.MovieInfo;
import com.gomo.movi_catalog_service.Repository.MovieInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieInfoController {


    @Autowired
    private MovieInfoRepository movieInfoRepository;

    @PostMapping("/save")
    public List<MovieInfo> saveAll(@RequestBody List<MovieInfo> moviInfoList){
        return movieInfoRepository.saveAll(moviInfoList);
    }

    @GetMapping("/list")
    public List<MovieInfo> getAll(){
        return movieInfoRepository.findAll();
    }

    @GetMapping("/find-path-by-id/{movieInfoId}")
    public String findPathById(@PathVariable Long movieInfoId){

        var videoInfoOptional = movieInfoRepository.findById(movieInfoId);
        return videoInfoOptional.map(MovieInfo::getPath).orElse(null);
    }

}
