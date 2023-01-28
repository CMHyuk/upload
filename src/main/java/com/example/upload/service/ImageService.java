package com.example.upload.service;

import com.example.upload.domain.Board;
import com.example.upload.domain.Image;
import com.example.upload.repository.BoardRepository;
import com.example.upload.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final BoardRepository boardRepository;

    @Value("${spring.servlet.multipart.location}")
    private String fileDir;

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    public List<Image> storeImages(List<MultipartFile> multipartFiles, Long boardId) throws IOException {
        List<Image> images = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                Image image = storeImage(multipartFile, boardId);
                images.add(image);
            }
        }
        return images;
    }

    public Image storeImage(MultipartFile multipartFile, Long boardId) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String storeImageName = createImageName(originalFilename, uuid);

        multipartFile.transferTo(new File(getFullPath(storeImageName)));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(IllegalArgumentException::new);

        Image image = Image.builder()
                .uploadName(originalFilename)
                .storeName(storeImageName)
                .board(board)
                .build();

        return imageRepository.save(image);
    }

    private String createImageName(String originalFilename, String uuid) {
        String ext = extractExt(originalFilename);
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
