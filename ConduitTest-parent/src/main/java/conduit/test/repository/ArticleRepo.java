package conduit.test.repository;

import conduit.test.repository.dao.DaoArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepo extends JpaRepository<DaoArticle, Integer> {

    DaoArticle findBySerial(String serial);

    @Query(value = "SELECT * FROM article a WHERE a.serial = :serial and a.vendeur_id = :vendeur_id",
            nativeQuery = true)
    DaoArticle findByStatusAndVendeurId(
            @Param("serial") String serial, @Param("vendeur_id") Integer vendeur_id);

    @Modifying
    @Query(value = "DELETE FROM article WHERE serial = :serial",
            nativeQuery = true)
    Integer deleteBySerial(String serial);
}