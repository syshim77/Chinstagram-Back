package com.landvibe.chinstagram.services;

import com.landvibe.chinstagram.models.Content;
import com.landvibe.chinstagram.repositories.ContentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ContentService {

    private ContentRepository contentRepository;

    public ContentService (ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public Page<Content> getContents(int skip, int limit) {
        return contentRepository.findAll(PageRequest.of(skip, limit));
    }

    public Content createContent(Content content) {
        return this.contentRepository.save(content);
    }

    public Content updateContent(Content content, int id) {
        return this.contentRepository.save(content);
    }

    public void deleteContent(int id) {}
}
