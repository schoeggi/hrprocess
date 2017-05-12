package ch.fhnw.recruiting;

import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.filter.Filter;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.camunda.bpm.engine.authorization.Authorization.AUTH_TYPE_GRANT;
import static org.camunda.bpm.engine.authorization.Permissions.ACCESS;
import static org.camunda.bpm.engine.authorization.Permissions.READ;
import static org.camunda.bpm.engine.authorization.Resources.APPLICATION;
import static org.camunda.bpm.engine.authorization.Resources.FILTER;

@Component
public class CamundaInitialDataGenerator {
/**
    private static final String ADMIN_USER = "admin";

    private static final Logger LOGGER = Logger.getLogger(CamundaInitialDataGenerator.class.getName());

    private final ProcessEngine processEngine;

    @Autowired
    public CamundaInitialDataGenerator(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    @PostConstruct
    public void createDemoUsers() {
        if (identityService().isReadOnly()) {
            LOGGER.info("Identity service provider is Read Only, not creating any demo users.");
            return;
        }

        if (identityService().createUserQuery().userId("hr1").singleResult() != null) {
            LOGGER.info("Not creating any demo users.");
            return;
        }

        LOGGER.info("Generating demo user for recruiting application");

        createUser("hr1");
        createUser("hr2");
        createUser("manager1");
        createUser("interviewer1");

        createGroup("hr");
        createGroup("manager");
        createGroup("interviewTeam");

        identityService().createMembership("hr1", "hr");
        identityService().createMembership("hr2", "hr");
        identityService().createMembership("manager1", "manager");
        identityService().createMembership("interviewer1", "interviewTeam");

        authorizeGroup("hr");
        authorizeGroup("manager");
        authorizeGroup("interviewTeam");

        createDefaultFilters();
    }

    private IdentityService identityService() {
        return processEngine.getIdentityService();
    }

    private void createUser(String userId) {
        User user = identityService().newUser(userId);
        user.setFirstName(userId);
        user.setLastName(userId);
        user.setPassword(userId);
        user.setEmail("test@test.ch");
        identityService().saveUser(user);
    }

    private void createGroup(String groupId) {
        Group group = identityService().newGroup(groupId);
        group.setName(groupId);
        group.setType("WORKFLOW");
        identityService().saveGroup(group);
    }

    private void authorizeGroup(String groupId) {
        Authorization tasklist = authorizationService().createNewAuthorization(AUTH_TYPE_GRANT);
        tasklist.setGroupId(groupId);
        tasklist.addPermission(ACCESS);
        tasklist.setResourceId("tasklist");
        tasklist.setResource(APPLICATION);
        authorizationService().saveAuthorization(tasklist);

        Authorization readProcessDefinition = authorizationService().createNewAuthorization(AUTH_TYPE_GRANT);
        readProcessDefinition.setGroupId(groupId);
        readProcessDefinition.addPermission(Permissions.READ);
        readProcessDefinition.addPermission(Permissions.READ_HISTORY);
        readProcessDefinition.setResource(Resources.PROCESS_DEFINITION);
        readProcessDefinition.setResourceId("*");
        authorizationService().saveAuthorization(readProcessDefinition);
    }

    private AuthorizationService authorizationService() {
        return processEngine.getAuthorizationService();
    }

    private void createDefaultFilters() {
        FilterService filterService = processEngine.getFilterService();

        if (filterService.getFilter("My Tasks") == null) {
            LOGGER.info("Generating default filters for recruiting application");
            Map<String, Object> filterProperties = new HashMap<>();
            filterProperties.put("description", "Tasks assigned to me");
            filterProperties.put("priority", -10);
            filterProperties.put("refresh", true);
            TaskService taskService = processEngine.getTaskService();
            TaskQuery query = taskService.createTaskQuery().taskAssigneeExpression("${currentUser()}");
            Filter myTasksFilter = filterService.newTaskFilter().setName("My Tasks").setProperties(filterProperties).setOwner(ADMIN_USER).setQuery(query);
            filterService.saveFilter(myTasksFilter);

            filterProperties.clear();
            filterProperties.put("description", "Tasks assigned to my Groups");
            filterProperties.put("priority", -5);
            filterProperties.put("refresh", true);
            query = taskService.createTaskQuery().taskCandidateGroupInExpression("${currentUserGroups()}").taskUnassigned();
            Filter groupTasksFilter = filterService.newTaskFilter().setName("My Group Tasks").setProperties(filterProperties).setOwner(ADMIN_USER).setQuery(query);
            filterService.saveFilter(groupTasksFilter);

            // global read authorizations for these filters
            Authorization globalMyTaskFilterRead = authorizationService().createNewAuthorization(Authorization.AUTH_TYPE_GLOBAL);
            globalMyTaskFilterRead.setResource(FILTER);
            globalMyTaskFilterRead.setResourceId(myTasksFilter.getId());
            globalMyTaskFilterRead.addPermission(READ);
            authorizationService().saveAuthorization(globalMyTaskFilterRead);

            Authorization globalGroupFilterRead = authorizationService().createNewAuthorization(Authorization.AUTH_TYPE_GLOBAL);
            globalGroupFilterRead.setResource(FILTER);
            globalGroupFilterRead.setResourceId(groupTasksFilter.getId());
            globalGroupFilterRead.addPermission(READ);
            authorizationService().saveAuthorization(globalGroupFilterRead);
        }
    }
*/
}
