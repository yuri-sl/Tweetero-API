package repository;

import entity.TweetsEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@ApplicationScoped
public class TweetsRepository implements PanacheRepository<TweetsEntity> {
}
