package org.voduybao.bookstorebackend.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.voduybao.bookstorebackend.dao.entities.Permission;

public interface PermissionRepository extends CrudRepository<Permission, Integer> {

}