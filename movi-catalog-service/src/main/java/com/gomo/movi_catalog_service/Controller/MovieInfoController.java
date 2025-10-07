package com.gomo.movi_catalog_service.Controller;


import com.gomo.movi_catalog_service.Model.MovieInfo;
import com.gomo.movi_catalog_service.Repository.MovieInfoRepository;
import jakarta.ws.rs.Path;
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


    // the ID of the movie info is given and the stream service need to return the path of  the movie

    @GetMapping("/find-path-by-id/{movieInfoId}")
     public String getPathById(@PathVariable Long id){
        var videoInfoOptional = movieInfoRepository.findById(id);
        return videoInfoOptional.map(MovieInfo::getPath).orElse(null); //  return videoInfoOptional.map(movieInfo -> movieInfo.getPath()).orElse(null);
    }

}
