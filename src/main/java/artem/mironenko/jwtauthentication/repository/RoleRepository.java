package artem.mironenko.jwtauthentication.repository;

import artem.mironenko.jwtauthentication.models.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
