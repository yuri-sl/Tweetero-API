package DTO.responses;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class CriarUsuarioDTOResponse {
    private Long id;

    @NotBlank(message = "Campo não pode ser nulo")
    private String avatar;

    @NotBlank(message = "Campo não pode ser nulo")
    private String username;
}
