package com.newx.muv.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by xuzhijian on 2018/7/2 0002.
 */
@RestController
@RequestMapping("/download")
public class DownloadController {

    @GetMapping("/file/{path}")
    ResponseEntity downloadFile(@PathVariable String path)
            throws IOException {
        FileSystemResource file = new FileSystemResource(path);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return (ResponseEntity<InputStreamResource>) ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"));
    }
}
