package pl.szczepaniak.chat.service;

import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.szczepaniak.chat.Utils.AppConfig;

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
        String name = profilePicturePath + id + ".jpg";
        File file = new File(name);
        uploadingFile.transferTo(file);
        resizePicture(name);
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

    private void resizePicture(String name) throws IOException {
        Thumbnails.of(new File(name))
                .size(AppConfig.IMG_SIZE, AppConfig.IMG_SIZE)
                .toFile(new File(name));
    }

}
