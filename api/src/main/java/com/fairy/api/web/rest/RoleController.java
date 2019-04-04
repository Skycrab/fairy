package com.fairy.api.web.rest;

import com.fairy.api.domain.Role;
import com.fairy.api.service.RoleService;
import com.fairy.api.util.ResponseUtil;
import com.fairy.api.web.rest.errors.CloudException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by yihaibo on 2019-04-03.
 */
@Api(description="角色相关")
@RestController
@Slf4j
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * POST  /roles : Create a new role.
     *
     * @param role the role to create
     * @return the ResponseEntity with status 201 (Created) and with body the new role, or with status 400 (Bad Request) if the role has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @ApiOperation(value = "创建角色")
    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role role) throws URISyntaxException {
        log.debug("REST request to save Role : {}", role);
        if(Objects.nonNull(role.getId())) {
            throw new CloudException("id exist:" + role.getId());
        }
        Role result = roleService.save(role);
        return ResponseEntity.created(new URI("/role/roles/" + role.getId()))
                .body(result);
    }

    /**
     * PUT  /roles : Updates an existing role.
     *
     * @param role the role to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated role,
     * or with status 400 (Bad Request) if the role is not valid,
     * or with status 500 (Internal Server Error) if the role couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @ApiOperation(value = "更新角色")
    @PutMapping("/roles")
    public ResponseEntity<Role> updateRole(@Valid @RequestBody Role role) {
        log.debug("REST request to update Role : {}", role);
        if(Objects.isNull(role.getId())) {
            throw new CloudException("id is null");
        }
        Role result = roleService.save(role);
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "查询角色")
    @GetMapping("/roles")
    public ResponseEntity<Page> getAllRules(Role role,
                                            @PageableDefault(value = 10)
                                            Pageable pageable) {
        log.debug("REST request to get all Roles by role: {}, pageable:{}", role, pageable);
        Page<Role> page = roleService.search(role, pageable);
        return ResponseEntity.ok().body(page);
    }

    /**
     * GET  /roles/:id : get the "id" role.
     *
     * @param id the id of the role to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the role, or with status 404 (Not Found)
     */
    @ApiOperation(value = "获取角色")
    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRole(@PathVariable long id) {
        log.debug("REST request to get Role : {}", id);
        Optional<Role> role = roleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(role);
    }

    /**
     * DELETE  /roles/:id : delete the "id" role.
     *
     * @param id the id of the role to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @ApiOperation(value = "删除角色")
    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        log.debug("REST request to delete Role : {}", id);
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }

}
