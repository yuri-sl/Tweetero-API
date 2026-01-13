package service;

import entity.pdfFileEntity;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.reactive.multipart.FileUpload;

@AllArgsConstructor
@ApplicationScoped
public class pdfFileService {
    @ConfigProperty(name = "app.storage.dir")
    String storageDir;

    public pdfFileEntity savePDF(FileUpload fileUpload){

    }

}
