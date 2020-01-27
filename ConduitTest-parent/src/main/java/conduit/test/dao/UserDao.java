package conduit.test.dao;

import conduit.test.model.DAOUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<DAOUser, Integer> {
//public interface UserDao extends CrudRepository<DAOUser, Integer> {

    DAOUser findByUsername(String username);
}