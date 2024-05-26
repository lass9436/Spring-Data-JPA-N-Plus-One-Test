package com.jpa_n_plus_one.test.domain_lazy;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String employeeName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee(String employeeName, Department department) {
        this.employeeName = employeeName;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeName='" + employeeName + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }
}
