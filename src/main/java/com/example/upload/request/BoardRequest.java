package com.example.upload.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BoardRequest {

    private String title;
    private String content;
    private List<MultipartFile> images;

}
