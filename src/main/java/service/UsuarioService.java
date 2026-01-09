package service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import repository.UsuarioRepository;

@AllArgsConstructor
//Dá erro se eu tirar o AllArgsConstructor do UsuarioService. Pq q eu preciso dele?
public class UsuarioService {
    final UsuarioRepository usuarioRepository;
}
