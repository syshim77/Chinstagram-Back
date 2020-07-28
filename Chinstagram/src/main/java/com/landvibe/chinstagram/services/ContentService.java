package com.landvibe.chinstagram.services;

import com.landvibe.chinstagram.models.Content;
import com.landvibe.chinstagram.models.Image;
import com.landvibe.chinstagram.repositories.ContentRepository;
import com.landvibe.chinstagram.repositories.ImageRepository;
import com.landvibe.chinstagram.uploader.S3Uploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContentService {

    @Autowired
    private S3Uploader s3Uploader;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ContentRepository contentRepository;

    public ContentService (ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    private List<Image> upload(MultipartFile[] multipartFiles) throws Exception {
        List<Image> contentImages = new ArrayList<>();

        for (int i=0; i<multipartFiles.length; i++) {
            String imageUrl = s3Uploader.upload(multipartFiles[i], "content");
            Image image = new Image(multipartFiles[i].getName(), imageUrl);
            imageRepository.save(image);
            contentImages.add(image);
        }

        return contentImages;
    }

    public Page<Content> getContents(int skip, int limit) {
        return contentRepository.findAll(PageRequest.of(skip, limit));
    }

    public Content createContent(MultipartFile[] contentImages, Content content) throws Exception {
        List<Image> uploadImages = upload(contentImages);
        content.setImages(uploadImages);

        return this.contentRepository.save(content);
    }

    public Content updateContent(MultipartFile[] contentImages, Content content, int id) throws Exception {
        if (!contentRepository.findById(id).isPresent()) {
            throw new AuthenticationException("This ID is not exist.");
        }

        List<Image> updateImages = upload(contentImages);

        Content findContent = Content.builder()
                .id(content.getId())
                .script(content.getScript())
                .images(updateImages)
                .createTime(content.getCreateTime())
                .updateTime(content.getUpdateTime())
                .build();

        return this.contentRepository.save(findContent);
    }

    public void deleteContent(int id) {
        this.contentRepository.deleteById(id);
    }
}
