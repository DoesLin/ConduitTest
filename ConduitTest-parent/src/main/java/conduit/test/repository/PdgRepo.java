package conduit.test.repository;

import conduit.test.repository.dao.DaoPdg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdgRepo extends JpaRepository<DaoPdg, Integer> {

    DaoPdg findByUsername(String username);
}