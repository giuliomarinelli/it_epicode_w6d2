package it.epicode.w6d2.services;

import it.epicode.w6d2.Models.Autore;
import it.epicode.w6d2.Models.Post;
import it.epicode.w6d2.exceptions.AutoreIdNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutoreService {
    private final List<Autore> autori = new ArrayList<>();

    public List<Autore> getAll() {
        return autori;
    }

    public Autore getById(UUID id) {
        Optional<Autore> opt = autori.stream().filter(a -> a.getId().equals(id))
                .findAny();
        return opt.orElse(null);
    }

    public Autore create(Autore a) {

        autori.add(a);
        return a;
    }

    public Autore update(UUID id, Autore nuovoAutore) {
        Autore a = getById(id);
        if (a == null) return null;
        a.setNome(nuovoAutore.getNome());
        a.setCognome(nuovoAutore.getCognome());
        a.setEmail(nuovoAutore.getEmail());
        a.setDataDiNascita(nuovoAutore.getDataDiNascita());
        return a;
    }

    public Autore delete(UUID id) {
        Autore a = getById(id);
        if (a == null) return null;
        autori.remove(a);
        return a;
    }

    public Autore getById(String id) {
        return getById(UUID.fromString(id));
    }

    public Autore update(String id, Autore nuovoAutore) throws AutoreIdNotFoundException {
        return update(UUID.fromString(id), nuovoAutore);
    }

    public Autore delete(String id) {
        return delete(UUID.fromString(id));
    }


}
