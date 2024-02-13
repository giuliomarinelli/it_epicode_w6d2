package it.epicode.w6d2.controllers;

import it.epicode.w6d2.Models.Autore;
import it.epicode.w6d2.services.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
public class AutoreController {

    @Autowired
    private AutoreService autoreSvc;

    @GetMapping("/autori")
    public List<Autore> getAll() {
        return autoreSvc.getAll();
    }

    @GetMapping("/autori/{id}")
    public Autore getById(@PathVariable UUID id) {
        Autore a = autoreSvc.getById(id);
        if (a == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return a;
    }

    @PostMapping("/autori")
    @ResponseStatus(HttpStatus.CREATED)
    public Autore create(@RequestBody Autore a) {
        return autoreSvc.create(a);
    }

    @PutMapping("/autori/{id}")
    public Autore update(@PathVariable UUID id, @RequestBody Autore a) {
        Autore nuovoAutore = autoreSvc.update(id, a);
        if (nuovoAutore == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author dosn't exist");
        return nuovoAutore;
    }

    @DeleteMapping("autori/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Autore delete(@PathVariable UUID id) {
        Autore a = autoreSvc.delete(id);
        if (a == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author dosn't exist");
        return a;
    }

}


