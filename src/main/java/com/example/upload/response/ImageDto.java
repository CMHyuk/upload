package com.example.upload.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class ImageDto {

    private final String uploadName;
    private final String storeName;

    @Builder
    public ImageDto(String uploadName, String storeName) {
        this.uploadName = uploadName;
        this.storeName = storeName;
    }
}
