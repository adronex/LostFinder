package by.lostFinder.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/api/posts/images")
public class FileUploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String upload(@RequestParam("file") MultipartFile file) throws IOException {

        byte[] bytes;
        if (!file.isEmpty()) {
            bytes = file.getBytes();
            //store file in db
        }

        return "some response";
    }
}
