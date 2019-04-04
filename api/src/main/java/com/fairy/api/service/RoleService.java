package com.fairy.api.service;

import com.fairy.api.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Created by yihaibo on 2019-04-02.
 */
public interface RoleService {
    Role save(Role role);

    Page<Role> findAll(Pageable pageable);

    Optional<Role> findOne(Long id);

    void delete(Long id);

    Page<Role> search(Role role, Pageable pageable);

}
