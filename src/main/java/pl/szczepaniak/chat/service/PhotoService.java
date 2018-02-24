package pl.szczepaniak.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class PhotoService {

    UserService userService;

    @Autowired
    public PhotoService(UserService userService) {
        this.userService = userService;
    }

    private final String profilePicturePath = System.getProperty("user.dir") + "/profile/";

    public void saveProfilePicture(String email,  MultipartFile uploadingFile) throws Exception {

        long id = userService.getIdByEmail(email);
        File file = new File(profilePicturePath + id + ".jpg");
        uploadingFile.transferTo(file);
    }

    public byte[] getProfilePicture(String email) throws Exception{
        long id = userService.getIdByEmail(email);
        String fileName = profilePicturePath + id + ".jpg";
        InputStream inputStream = new FileInputStream(fileName);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[512];
        int l = inputStream.read(buffer);
        while(l >= 0) {
            outputStream.write(buffer, 0, l);
            l = inputStream.read(buffer);
        }
        return outputStream.toByteArray();
    }

}
