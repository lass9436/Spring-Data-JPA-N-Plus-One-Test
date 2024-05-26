package com.jpa_n_plus_one.test.queryTest;

import com.jpa_n_plus_one.test.domain_lazy.Department;
import com.jpa_n_plus_one.test.domain_lazy.Employee;
import com.jpa_n_plus_one.test.domain_lazy.Project;
import com.jpa_n_plus_one.test.repository_lazy.DepartmentRepository;
import com.jpa_n_plus_one.test.repository_lazy.EmployeeRepository;
import com.jpa_n_plus_one.test.repository_lazy.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
public class LazyTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    PlatformTransactionManager transactionManager;

    @BeforeEach
    void setUp(){
        Department department1 = new Department("department1");
        Department department2 = new Department("department2");
        Department department3 = new Department("department3");

        Employee employee11 = new Employee("employee1", department1);
        Employee employee12 = new Employee("employee2", department1);
        Employee employee13 = new Employee("employee3", department1);

        Employee employee21 = new Employee("employee1", department2);
        Employee employee22 = new Employee("employee2", department2);
        Employee employee23 = new Employee("employee3", department2);

        Employee employee31 = new Employee("employee1", department3);
        Employee employee32 = new Employee("employee2", department3);
        Employee employee33 = new Employee("employee3", department3);

        Project project11 = new Project("project1", department1);
        Project project12 = new Project("project2", department1);
        Project project13 = new Project("project3", department1);

        Project project21 = new Project("project1", department2);
        Project project22 = new Project("project2", department2);
        Project project23 = new Project("project3", department2);

        Project project31 = new Project("project1", department3);
        Project project32 = new Project("project2", department3);
        Project project33 = new Project("project3", department3);

        departmentRepository.save(department1);
        departmentRepository.save(department2);
        departmentRepository.save(department3);

        employeeRepository.save(employee11);
        employeeRepository.save(employee12);
        employeeRepository.save(employee13);

        employeeRepository.save(employee21);
        employeeRepository.save(employee22);
        employeeRepository.save(employee23);

        employeeRepository.save(employee31);
        employeeRepository.save(employee32);
        employeeRepository.save(employee33);

        projectRepository.save(project11);
        projectRepository.save(project12);
        projectRepository.save(project13);

        projectRepository.save(project21);
        projectRepository.save(project22);
        projectRepository.save(project23);

        projectRepository.save(project31);
        projectRepository.save(project32);
        projectRepository.save(project33);
    }

    @AfterEach
    void tearDown(){
        projectRepository.deleteAll();
        employeeRepository.deleteAll();
        departmentRepository.deleteAll();
    }

    @Test
    @DisplayName("FetchType.Lazy 일 때 no session exception")
    void fetchTypeLazyNoSession(){
        log.info("레포지토리에서 부서 조회 (no Session)");
        List<Department> departments = departmentRepository.findAll();

        assertThrows(LazyInitializationException.class, ()->{
            for(Department department : departments){
                // could not initialize proxy - no Session
                for(Employee employee : department.getEmployees()){
                    log.info(employee.toString());
                }
            }
        });
    }

    @Test
    @DisplayName("FetchType.Lazy 일 때, N+1 쿼리 테스트")
    void fetchTypeLazyNPlusOne(){
        TransactionStatus selectTransaction = transactionManager.getTransaction(new TransactionDefinition() {});
        log.info("레포지토리에서 부서 조회 (non fetch)");
        List<Department> departments = departmentRepository.findAll();
            for(Department department : departments){
            log.info("{} 부서의 직원 조회 (non fetch)", department.getDepartmentName());
            for(Employee employee : department.getEmployees()){
                log.info(employee.toString());
            }
        }
        assertThat(departments).hasSize(3);
        transactionManager.commit(selectTransaction);
    }

    @Test
    @DisplayName("FetchType.Lazy 일 때 join fetch 로 쿼리 테스트")
    void fetchTypeLazyJoinFetch(){
        TransactionStatus selectTransaction = transactionManager.getTransaction(new TransactionDefinition() {});
        log.info("레포지토리에서 부서 조회 (fetch)");
        List<Department> departments = departmentRepository.findAllFetch();
        for(Department department : departments){
            log.info("{} 부서의 직원 조회 (fetch)", department.getDepartmentName());
            for(Employee employee : department.getEmployees()){
                log.info(employee.toString());
            }
        }
        assertThat(departments).hasSize(3);
        transactionManager.commit(selectTransaction);
    }

    @Test
    @DisplayName("FetchType.Lazy 일 때 batchSize = 5 로 쿼리 테스트")
    void fetchTypeLazyBatchSize(){
        TransactionStatus selectTransaction = transactionManager.getTransaction(new TransactionDefinition() {});
        log.info("레포지토리에서 부서 조회 (batch)");
        List<Department> departments = departmentRepository.findAll();
        for(Department department : departments){
            log.info("{} 부서의 프로젝트 조회 (batch)", department.getDepartmentName());
            for(Project project : department.getProjects()){
                log.info(project.toString());
            }
        }
        assertThat(departments).hasSize(3);
        transactionManager.commit(selectTransaction);
    }
}
