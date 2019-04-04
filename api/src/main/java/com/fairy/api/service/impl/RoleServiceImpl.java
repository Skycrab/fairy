package com.fairy.api.service.impl;

import com.fairy.api.domain.Role;
import com.fairy.api.repository.RoleRepository;
import com.fairy.api.service.RoleService;
import com.github.wenhao.jpa.Specifications;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by yihaibo on 2019-04-02.
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        log.debug("Request to save Role : {}", role);
        Role result = roleRepository.save(role);

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Role> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Role> findOne(Long id) {
        log.debug("Request to get Role : {}", id);
        return roleRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Role : {}", id);
        roleRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Role> search(Role role, Pageable pageable) {
        Specification<Role> specification = Specifications.<Role>and()
                .like(StringUtils.isNotBlank(role.getRuleName()), "ruleName", "%" + role.getRuleName() + "%")
                .build();

        return roleRepository.findAll(specification, pageable);
    }
}
