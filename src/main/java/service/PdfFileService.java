package service;

import entity.PdfFileEntity;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.IOException;
import java.nio.file.*;
import java.time.Instant;
import java.util.UUID;

@ApplicationScoped
public class PdfFileService {

    @ConfigProperty(name = "app.storage.dir")
    String storageDir;

    public PdfFileEntity savePDF(FileUpload fileUpload) {
        if (fileUpload == null) throw new RuntimeException("Arquivo não foi encontrado");
        if (!"application/pdf".equalsIgnoreCase(fileUpload.contentType()))
            throw new IllegalArgumentException("Apenas tipo PDF é permitido");

        try {
            Path dir = Paths.get(storageDir).toAbsolutePath().normalize();
            Files.createDirectories(dir);

            String storedName = UUID.randomUUID() + ".pdf"; // ✅
            Path target = dir.resolve(storedName).normalize();

            Files.move(fileUpload.uploadedFile(), target, StandardCopyOption.REPLACE_EXISTING);

            PdfFileEntity pdf = PdfFileEntity.builder()
                    .originalName(fileUpload.fileName())
                    .storedName(storedName)
                    .contentType(fileUpload.contentType())
                    .size(Files.size(target)) // ✅ mais confiável que fileUpload.size()
                    .created_at(Instant.now())
                    .build();

            pdf.persist(); // ✅ se você quiser salvar no banco
            return pdf;

        } catch (IOException e) {
            throw new RuntimeException("Falha ao salvar PDF", e);
        }
    }

    public Path resolvePathOrThrow(PdfFileEntity pdfFileEntity) {
        Path dir = Paths.get(storageDir).toAbsolutePath().normalize();
        Path p = dir.resolve(pdfFileEntity.storedName).normalize();

        if (!p.startsWith(dir)) throw new RuntimeException("Path inválido");
        if (!Files.exists(p)) throw new RuntimeException("Arquivo não encontrado");
        return p;
    }
}
