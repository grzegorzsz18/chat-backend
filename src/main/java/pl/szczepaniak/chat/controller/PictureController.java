package pl.szczepaniak.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.szczepaniak.chat.service.PhotoService;
import pl.szczepaniak.chat.service.UserService;

@RestController
@RequestMapping("/picture")
public class PictureController {


    PhotoService photoService;
    UserService userService;

    @Autowired
    public PictureController(PhotoService photoService, UserService userService) {
        this.photoService = photoService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity addProfilePicture(@RequestParam(value = "email") String email,
                                            @RequestParam(value = "fileKey")MultipartFile picture) throws Exception{
        photoService.saveProfilePicture(email, picture);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{email:.+}")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable("email") String  email) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "image/png");
        return new ResponseEntity<byte[]>(photoService.getProfilePicture(email), headers, HttpStatus.OK);
    }
}
