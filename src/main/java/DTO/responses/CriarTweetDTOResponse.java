package DTO.responses;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ApplicationScoped
public class CriarTweetDTOResponse {
    @NotBlank(message = "Campo obrigatório!")
    public long id;


    @NotBlank(message = "Campo obrigatório!")
    @Min(message = "A mensagem precisa ter o tamanho mínimo de 10 caracteres",value = 10)
    public String text;


    public CriarUsuarioDTOResponse criarUsuarioDTOResponse;

}
