package it.epicode.w6d2.Models;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;
@Data
public class Autore {
    private UUID id;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNascita;
    private String avatar;

    public Autore(String nome, String cognome, String email, LocalDate dataDiNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataDiNascita = dataDiNascita;
        id = UUID.randomUUID();
        avatar = "https://ui-avatars.com/api/?name=" + nome + "+" + cognome;
    }
}
