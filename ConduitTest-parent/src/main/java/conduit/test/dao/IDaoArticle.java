package conduit.test.dao;

import conduit.test.dao.impl.DaoArticle;
import conduit.test.dao.impl.DaoVendeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDaoArticle extends JpaRepository<DaoArticle, Integer> {

    DaoArticle findBySerial(String serial);

}