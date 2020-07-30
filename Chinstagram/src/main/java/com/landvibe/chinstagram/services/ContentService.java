package com.landvibe.chinstagram.services;

import com.landvibe.chinstagram.models.Content;
import com.landvibe.chinstagram.models.Image;
import com.landvibe.chinstagram.repositories.ContentRepository;
import com.landvibe.chinstagram.repositories.ImageRepository;
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
    private ImageRepository imageRepository;

    @Autowired
    private ContentRepository contentRepository;

    public ContentService (ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public Page<Content> getContents(int skip, int limit) {
        return contentRepository.findAll(PageRequest.of(skip, limit));
    }

    public Content createContent(MultipartFile[] contentImages, Content content) throws Exception {
        return this.contentRepository.save(content);
    }

    public Content updateContent(MultipartFile[] contentImages, Content content, int id) throws Exception {
        if (!contentRepository.findById(id).isPresent()) {
            throw new AuthenticationException("This ID is not exist.");
        }

        Content findContent = Content.builder()
                .id(content.getId())
                .script(content.getScript())
                .images(contentImages)
                .createTime(content.getCreateTime())
                .updateTime(content.getUpdateTime())
                .build();

        return this.contentRepository.save(findContent);
    }

    public void deleteContent(int id) {
        this.contentRepository.deleteById(id);
    }
}
