package repository;

import entity.PdfFileEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationScoped
public class PdfFileRepository implements PanacheRepository<PdfFileEntity> {
}
