package resource;

import DTO.CriarUsuarioDTO;
import DTO.responses.CriarUsuarioDTOResponse;
import entity.UsuarioEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.resteasy.reactive.RestResponse;
import service.UsuarioService;

@AllArgsConstructor


@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UsuarioResource {
    final UsuarioService usuarioService;

    @POST
    public RestResponse<?> criarUsuarioNovo(@RequestBody CriarUsuarioDTO usuarioDTO){
        try {
            CriarUsuarioDTOResponse criarUsuarioDTOResponse = usuarioService.adicionarUsuario(usuarioDTO);
            return RestResponse.status(Response.Status.CREATED,criarUsuarioDTOResponse);
        }
        catch (RuntimeException e) {
            return RestResponse.status(Response.Status.CONFLICT,e.getMessage());
        }
    };
    @GET
    public RestResponse<?> teste(){
        return RestResponse.status(RestResponse.Status.OK);
    }
}
