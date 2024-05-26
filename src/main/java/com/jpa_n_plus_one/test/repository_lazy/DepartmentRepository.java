package com.jpa_n_plus_one.test.repository_lazy;

import com.jpa_n_plus_one.test.domain_lazy.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("select d from Department d join fetch d.employees")
    List<Department> findAllFetch();
}
