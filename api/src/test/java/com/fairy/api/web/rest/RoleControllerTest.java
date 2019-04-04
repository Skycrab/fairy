package com.fairy.api.web.rest;

import com.fairy.api.App;
import com.fairy.api.domain.Role;
import com.fairy.api.repository.RoleRepository;
import com.fairy.api.service.RoleService;
import com.fairy.api.web.rest.errors.ExceptionHandling;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by yihaibo on 2019-04-03.
 */
@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
@Slf4j
public class RoleControllerTest {

    private static final String DEFAULT_RULE_NAME = "name";
    private static final String DEFAULT_RULE_DESC = "desc";

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ExceptionHandling exceptionHandling;

    @Autowired
    private Validator validator;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc roleMockMvc;

    private Role role;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RoleController roleController = new RoleController(roleService);
        this.roleMockMvc = MockMvcBuilders.standaloneSetup(roleController)
                .setCustomArgumentResolvers(pageableHandlerMethodArgumentResolver)
                .setControllerAdvice(exceptionHandling)
                .setValidator(validator)
                .build();

    }

    public static Role createEntiry(EntityManager em) {
        Role role = new Role();
        role.setRuleName(DEFAULT_RULE_NAME);
        role.setRuleDesc(DEFAULT_RULE_DESC);
        return role;
    }

    @Before
    public void initTest() {
        role = createEntiry(em);
    }

    @Test
    @Transactional
    public void createRole() throws Exception {
        int beforeSize = roleRepository.findAll().size();

        roleMockMvc.perform(post("/roles")
                .contentType(TestUtil.JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(role)))
                .andExpect(status().isCreated());

        List<Role> roleList = roleRepository.findAll();
        assertThat(roleList.size() == beforeSize+1);
    }

    @Test
    @Transactional
    public void createRoleWithId() throws Exception {
        int beforeSize = roleRepository.findAll().size();

        role.setId(1L);

        roleMockMvc.perform(post("/roles")
                .contentType(TestUtil.JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(role)))
                .andExpect(status().isBadRequest());

        List<Role> roleList = roleRepository.findAll();
        assertThat(roleList).hasSize(beforeSize);
    }

    @Test
    @Transactional
    public void getRole() throws Exception {
        roleRepository.saveAndFlush(role);

        roleMockMvc.perform(get("/roles/{id}", role.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.JSON_UTF8))
                .andExpect(jsonPath("$.id").value(role.getId()))
                .andExpect(jsonPath("$.ruleName").value(DEFAULT_RULE_NAME))
                .andExpect(jsonPath("$.ruleDesc").value(DEFAULT_RULE_DESC));
    }

    @Test
    @Transactional
    public void getAllRolse() throws Exception {
        roleRepository.saveAndFlush(role);

        roleMockMvc.perform(get("/roles?ruleName=name&pageNumber=0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[*].id").value(Matchers.hasItem(role.getId().intValue())))
                .andExpect(jsonPath("$.content.[*].ruleName").value(Matchers.hasItem(DEFAULT_RULE_NAME)))
                .andExpect(jsonPath("$.content.[*].ruleDesc").value(Matchers.hasItem(DEFAULT_RULE_DESC)));
    }

}
