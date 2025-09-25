package com.gomo.movie_streaming_service.Controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
public class MovieStreamController {

    public static final String VIDEO_DIRECTORY = "C:\\Users\\ASUS\\Videos\\";

    @GetMapping("/stream/{videoName}")
    public ResponseEntity<InputStreamResource> streamVideo(@PathVariable String videoName) throws FileNotFoundException {
        File file = new File(VIDEO_DIRECTORY + videoName);

        if (file.exists()) {
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("video/mp4"))
                    .body(inputStreamResource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
