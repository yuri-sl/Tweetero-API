package resource;

import DTO.responses.PdfDTOResponse;
import entity.PdfFileEntity;
import io.vertx.ext.web.FileUpload;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestResponse;
import service.PdfFileService;

import java.awt.*;

@AllArgsConstructor
@ApplicationScoped

@Path("/pdfs")
public class PdfFileResource {
    @Inject
    PdfFileService pdfFileService;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public RestResponse<PdfFileEntity> upload(@RestForm("file") FileUpload file) {
        PdfFileEntity saved = pdfFileService.savePDF((org.jboss.resteasy.reactive.multipart.FileUpload) file);
        return RestResponse.status(RestResponse.Status.ACCEPTED, saved);
    }

    @GET
    @Path("/{id}")
    public Response download(@PathParam("id") Long id) {
        PdfFileEntity e = PdfFileEntity.findById(id);
        if (e == null) throw new NotFoundException("PDF n encontrado");

        Path path = (Path) pdfFileService.resolvePathOrThrow(e);

        return Response.ok(path.toString(), e.contentType)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + e.originalName.replace("\"", "") + "\"")
                .build();
    }
    public record PdfResponseDTO(Long id, String originalName) {}
}
