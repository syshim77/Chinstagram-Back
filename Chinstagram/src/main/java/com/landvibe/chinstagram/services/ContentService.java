package com.landvibe.chinstagram.services;

import com.landvibe.chinstagram.models.Content;
import com.landvibe.chinstagram.repositories.ContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ContentService {

    private ContentRepository contentRepository;

    public ContentService (ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public List<Content> getContents(int skip, int limit) {
        return this.contentRepository.findAll();
    }

    public Content createContent(Content content) {
        return this.contentRepository.save(content);
    }

    public Content updateContent(Content content, int id) {
        return this.contentRepository.save(content);
    }

    public void deleteContent(int id) {
    }
}
