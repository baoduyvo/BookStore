package org.voduybao.bookstorebackend.dao.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.voduybao.bookstorebackend.dao.entities.Role;


public interface RoleRepository extends CrudRepository<Role, Integer> {

    @Query(value = "SELECT * FROM roles WHERE role_name = 'CUSTOMER' LIMIT 1", nativeQuery = true)
    Role getCustomerRole();

    @Query(value = "SELECT * FROM roles WHERE role_name = 'ADMIN' LIMIT 1", nativeQuery = true)
    Role getAdminRole();

}