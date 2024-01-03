package net.javaguides.springboot.springboot.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import net.javaguides.springboot.springboot.integration.AbstractContainerBaseTest;
import net.javaguides.springboot.springboot.model.Employee;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
//public class EmployeeRepositoryIT extends AbstractContainerBaseTest {
public class EmployeeRepositoryIT  {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setup() {
        employee = Employee.builder().firstName("Marcin").lastName("Lukowski").email("lukowskimarcin@gmail.com")
                .build();
    }

    @Test
    @DisplayName("JUnit test for save employee operation")
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
        // given - prediction or setup
        // when
        Employee savedEmployee = employeeRepository.save(employee);

        // then
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @Test
    public void givenEmployeesList_whenFindAll_thenEmployeesList() {
        // given - prediction or setup
        var emp2 = Employee.builder().firstName("John").lastName("Wick").email("wickjohn@gmail.com").build();

        employeeRepository.save(employee);
        employeeRepository.save(emp2);

        // when - action or the behavior that we are going test
        var employeeList = employeeRepository.findAll();

        // then - verify output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @Test
    public void givenEmployee_whenGetById_thenEmployee() {
        // given - prediction or setup
        employeeRepository.save(employee);

        // when - action or the behavior that we are going test
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        // then - verify output
        assertThat(employeeDB).isNotNull();
    }

    @Test
    public void givenEmployeeEmail_whenGetByEmail_thenEmployeeObject() {
        // given - prediction or setup

        employeeRepository.save(employee);

        // when - action or the behavior that we are going test
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();
        // then - verify output
        assertThat(employeeDB).isNotNull();
    }

    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        // given - prediction or setup

        var savedEmployee = employeeRepository.save(employee);

        // when - action or the behavior that we are going test
        savedEmployee.setEmail("newemail.@gmail.com");
        savedEmployee.setFirstName("John");
        Employee employeeDB = employeeRepository.save(savedEmployee);

        // then - verify output
        assertThat(employeeDB.toString()).isNotSameAs(savedEmployee.toString());
        assertThat(employeeDB).isNotNull();
        assertThat(employeeDB.getFirstName()).isEqualTo("John");
        assertThat(employeeDB.getEmail()).isEqualTo("newemail.@gmail.com");
    }

    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {
        // given - prediction or setup

        var savedEmployee = employeeRepository.save(employee);

        // when - action or the behavior that we are going test
        employeeRepository.delete(savedEmployee);
        var employeeOptional = employeeRepository.findById(savedEmployee.getId());

        // then - verify output
        assertThat(employeeOptional).isEmpty();
    }

    @Test
    public void givenFirstAndLastName_whenFindByJPQL_thenReturnEmployee() {
        // given - prediction or setup
        String firstName = "Marcin";
        String lastName = "Lukowski";

        employeeRepository.save(employee);

        // when - action or the behavior that we are going test
        var result = employeeRepository.findByJPQL(firstName, lastName);

        // then - verify output
        assertThat(result).isNotNull();
    }

    @Test
    public void givenFirstAndLastName_whenFindByJPQLNamedParams_thenReturnEmployee() {
        // given - prediction or setup
        String firstName = "Marcin";
        String lastName = "Lukowski";

        employeeRepository.save(employee);

        // when - action or the behavior that we are going test
        var result = employeeRepository.findByJPQLNamedParams(firstName, lastName);

        // then - verify output
        assertThat(result).isNotNull();
    }

    @Test
    public void givenFirstAndLastName_whenFindByNativeSQL_thenReturnEmployee() {
        // given - prediction or setup
        String firstName = "Marcin";
        String lastName = "Lukowski";

        employeeRepository.save(employee);

        // when - action or the behavior that we are going test
        var result = employeeRepository.findByNativeSQL(firstName, lastName);

        // then - verify output
        assertThat(result).isNotNull();
    }

    @Test
    public void givenFirstAndLastName_whenFindByNativeSQLNamed_thenReturnEmployee() {
        // given - prediction or setup
        String firstName = "Marcin";
        String lastName = "Lukowski";

        employeeRepository.save(employee);

        // when - action or the behavior that we are going test
        var result = employeeRepository.findByNativeSQLNamed(firstName, lastName);

        // then - verify output
        assertThat(result).isNotNull();
    }

}
