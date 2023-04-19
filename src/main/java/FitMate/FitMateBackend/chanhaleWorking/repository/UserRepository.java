package FitMate.FitMateBackend.chanhaleWorking.repository;

import FitMate.FitMateBackend.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    @PersistenceContext
    private final EntityManager em;

    public Optional<User> findByLoginId(String loginId) {
        return em.createQuery("select u from User u where u.loginId is " + loginId, User.class)
                .getResultList().stream().findFirst();
    }
}
