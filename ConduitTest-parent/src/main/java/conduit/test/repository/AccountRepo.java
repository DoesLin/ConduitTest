package conduit.test.repository;

import conduit.test.repository.dao.DaoAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<DaoAccount, Integer> {
//public interface UserDao extends CrudRepository<DAOUser, Integer> {

    DaoAccount findByUsername(String username);
}