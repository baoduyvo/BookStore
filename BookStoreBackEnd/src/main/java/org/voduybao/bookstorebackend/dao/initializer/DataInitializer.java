package org.voduybao.bookstorebackend.dao.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.Permission;
import org.voduybao.bookstorebackend.dao.entities.Role;
import org.voduybao.bookstorebackend.dao.repositories.PermissionRepository;
import org.voduybao.bookstorebackend.dao.repositories.RoleRepository;
import org.voduybao.bookstorebackend.tools.contants.RoleEnum;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public DataInitializer(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>> START INIT DATABASE");
        long countRoles = this.roleRepository.count();
        long countPermissions = this.permissionRepository.count();

        if (countRoles == 0) {
            Role adminRole = new Role();
            adminRole.setRoleName(RoleEnum.AMIDN);
            adminRole.setDescription("Administrator role");
            roleRepository.save(adminRole);

            Role customerRole = new Role();
            customerRole.setRoleName(RoleEnum.CUSTOMER);
            customerRole.setDescription("Customer role");
            roleRepository.save(customerRole);
        }

        if (countPermissions == 0) {
            Permission adminPermission = new Permission();
            adminPermission.setPermissionName("admin");
            adminPermission.setDescription("Administrator permission");
            permissionRepository.save(adminPermission);

            Permission customerPermission = new Permission();
            customerPermission.setPermissionName("customer");
            customerPermission.setDescription("Customer permission");
            permissionRepository.save(customerPermission);
        }

    }
}