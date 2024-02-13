package it.epicode.w6d2.Models;

import lombok.Data;

import java.util.UUID;

@Data
public class Post {
    UUID id = UUID.randomUUID();
    UUID autoreId;
    String categoria;
    String titolo;
    String cover;
    String contenuto;
    int tempoDiLettura;
}
