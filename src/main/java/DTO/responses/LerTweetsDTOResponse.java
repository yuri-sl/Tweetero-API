package DTO.responses;

import entity.UsuarioEntity;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@ApplicationScoped
public class LerTweetsDTOResponse {
    public long id;
    public String text;
    public UsuarioEntity usuarioEntity;
}
