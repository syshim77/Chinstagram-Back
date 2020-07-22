package com.landvibe.chinstagram.services;

import com.landvibe.chinstagram.models.Content;
import com.landvibe.chinstagram.repositories.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    public ContentService (ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public Page<Content> getContents(int skip, int limit) {
        return contentRepository.findAll(PageRequest.of(skip, limit));
    }

    public Content createContent(Content content) {
        Content newContent = Content.builder()
                .id(content.getId())
                .script(content.getScript())
                .images(content.getImages())
                .createTime(content.getCreateTime())
                .updateTime(content.getUpdateTime())
                .build();

        return this.contentRepository.save(newContent);
    }

    public Content updateContent(Content content, int id) {
        if (!contentRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("This ID is not exist.");
        }

        Content findContent = Content.builder()
                .id(content.getId())
                .script(content.getScript())
                .images(content.getImages())
                .createTime(content.getCreateTime())
                .updateTime(content.getUpdateTime())
                .build();

        return this.contentRepository.save(findContent);
    }

    public void deleteContent(int id) {}
}
