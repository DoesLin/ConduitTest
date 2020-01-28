package conduit.test.controller;

import java.util.ArrayList;
import java.util.List;

import conduit.test.model.EmployeeMock;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping({"/employees"})
public class TestController {

    private List<EmployeeMock> employeeMocks = createList();

    @GetMapping(produces = "application/json")
    public List<EmployeeMock> firstPage() {
        return employeeMocks;
    }

    @DeleteMapping(path = {"/{id}"})
    public EmployeeMock delete(@PathVariable("id") int id) {
        EmployeeMock deletedEmp = null;
        for (EmployeeMock emp : employeeMocks) {
            if (emp.getEmpId().equals(id)) {
                employeeMocks.remove(emp);
                deletedEmp = emp;
                break;
            }
        }
        return deletedEmp;
    }

    @PostMapping
    public EmployeeMock create(@RequestBody EmployeeMock user) {
        employeeMocks.add(user);
        return user;
    }

    private static List<EmployeeMock> createList() {
        List<EmployeeMock> tempEmployeeMocks = new ArrayList<>();
        EmployeeMock emp1 = new EmployeeMock();
        emp1.setName("emp1");
        emp1.setDesignation("manager");
        emp1.setEmpId("1");
        emp1.setSalary(3000);

        EmployeeMock emp2 = new EmployeeMock();
        emp2.setName("emp2");
        emp2.setDesignation("developer");
        emp2.setEmpId("2");
        emp2.setSalary(3000);
        tempEmployeeMocks.add(emp1);
        tempEmployeeMocks.add(emp2);
        return tempEmployeeMocks;
    }
}