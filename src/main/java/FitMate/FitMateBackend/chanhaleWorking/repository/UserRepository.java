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

    public void save(User user){
        if (user.getId() == null) {
            em.persist(user);
        }else{
            em.merge(user);
        }
    }
    public Optional<User> findByLoginId(String loginId) {
        return em.createQuery("select u from User u where u.loginId = :loginId ", User.class)
                .setParameter("loginId", loginId)
                .getResultList().stream().findFirst();
    }

    public Boolean CheckDuplicatedLoginId(String loginId){
        return em.createQuery("select u from User u where u.loginId = :loginId ", User.class)
                .setParameter("loginId", loginId)
                .getResultList().size() > 0;
    }
}
