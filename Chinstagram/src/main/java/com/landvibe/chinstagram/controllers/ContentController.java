package com.landvibe.chinstagram.controllers;

import com.landvibe.chinstagram.models.Content;
import com.landvibe.chinstagram.services.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Page<Content> getContents(@RequestParam int skip, @RequestParam int limit) {
        return this.contentService.getContents(skip, limit);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Content createContent(@RequestBody Content content) {
        return this.contentService.createContent(content);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Content updateContent(@RequestBody Content content, @PathVariable int id) throws Exception {
        return this.contentService.updateContent(content, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContent(@PathVariable int id) {
        this.contentService.deleteContent(id);
    }
}
