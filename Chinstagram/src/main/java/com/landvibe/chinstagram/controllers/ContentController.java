package com.landvibe.chinstagram.controllers;

import com.landvibe.chinstagram.custom.CheckJwt;
import com.landvibe.chinstagram.models.Content;
import com.landvibe.chinstagram.services.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CheckJwt
@RestController
@RequestMapping("/chinstagram/content")
@CrossOrigin
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
    public Content createContent(@RequestParam("images") MultipartFile[] contentImages,
                                 @RequestParam("id") Integer contentId,
                                 @RequestParam("script") String contentScript) throws Exception {
        Content content = Content.builder()
                .id(contentId)
                .script(contentScript)
                .build();
        return this.contentService.createContent(contentImages, content);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Content updateContent(@RequestParam("images") MultipartFile[] contentImages,
                                 @RequestParam("id") Integer contentId,
                                 @RequestParam("script") String contentScript,
                                 @PathVariable int id) throws Exception {
        Content content = Content.builder()
                .id(contentId)
                .script(contentScript)
                .build();
        return this.contentService.updateContent(contentImages, content, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContent(@PathVariable int id) {
        this.contentService.deleteContent(id);
    }
}
