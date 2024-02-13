package it.epicode.w6d2.controllers;

import it.epicode.w6d2.Models.Autore;
import it.epicode.w6d2.Models.Post;
import it.epicode.w6d2.exceptions.AutoreIdNotFoundException;
import it.epicode.w6d2.services.AutoreService;
import it.epicode.w6d2.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
public class PostController {
    @Autowired
    private PostService postSvc;

    @GetMapping("/post")
    public List<Post> getAll() {
        return postSvc.getAll();
    }

    @GetMapping("/post/{id}")
    public Post getById(@PathVariable UUID id) {
        Post a = postSvc.getById(id);
        if (a == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return a;
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post p) {
        try {
        return postSvc.create(p);
        } catch (AutoreIdNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "autoreId doesn't refer to any author");
        }

    }

    @PutMapping("/post/{id}")
    public Post update(@PathVariable UUID id, @RequestBody Post p) {
        try {
            Post nuovoPost = postSvc.update(id, p);
            if (nuovoPost == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author dosn't exist");
            return nuovoPost;
        } catch (AutoreIdNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "autoreId doesn't refer to any author");
        }
    }

    @DeleteMapping("post/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Post delete(@PathVariable UUID id) {
        Post a = postSvc.delete(id);
        if (a == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author dosn't exist");
        return a;
    }

}
