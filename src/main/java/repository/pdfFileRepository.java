package repository;

import entity.pdfFileEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationScoped
public class pdfFileRepository implements PanacheRepository<pdfFileEntity> {
}
