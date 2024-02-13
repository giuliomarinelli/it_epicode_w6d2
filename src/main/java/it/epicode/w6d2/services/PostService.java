package it.epicode.w6d2.services;

import it.epicode.w6d2.Models.Autore;
import it.epicode.w6d2.Models.Post;
import it.epicode.w6d2.exceptions.AutoreIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {
    @Autowired
    private AutoreService autoreSvc;
    private final List<Post> posts = new ArrayList<>();

    public List<Post> getAll() {
        return posts;
    }

    public Post getById(UUID id) {
        Optional<Post> opt = posts.stream().filter(a -> a.getId().equals(id))
                .findAny();
        return opt.orElse(null);
    }

    public Post create(Post p) throws AutoreIdNotFoundException {
        if (!autoreSvc.getAll().stream().map(Autore::getId).toList().contains(p.getAutoreId()))
            throw new AutoreIdNotFoundException();
        posts.add(p);
        return p;
    }

    public Post update(UUID id, Post nuovoPost) throws AutoreIdNotFoundException {
        Post p = getById(id);
        if (p == null) return null;
        if (!autoreSvc.getAll().stream().map(Autore::getId).toList().contains(nuovoPost.getAutoreId()))
            throw new AutoreIdNotFoundException();
        p.setCategoria(nuovoPost.getCategoria());
        p.setTitolo(nuovoPost.getTitolo());
        p.setCover(nuovoPost.getCover());
        p.setContenuto(nuovoPost.getContenuto());
        p.setTempoDiLettura(nuovoPost.getTempoDiLettura());

        return p;
    }

    public Post delete(UUID id) {
        Post p = getById(id);
        if (p == null) return null;
        posts.remove(p);
        return p;
    }

    public Post getById(String id) {
        return getById(UUID.fromString(id));
    }

    public Post update(String id, Post nuovoPost) throws AutoreIdNotFoundException {
        return update(UUID.fromString(id), nuovoPost);
    }

    public Post delete(String id) {
        return delete(UUID.fromString(id));
    }

}
