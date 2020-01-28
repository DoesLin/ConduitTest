package conduit.test.repository;

import conduit.test.repository.dao.DaoArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepo extends JpaRepository<DaoArticle, Integer> {

    DaoArticle findBySerial(String serial);

    List<DaoArticle> findByVendeurId(long id);

}