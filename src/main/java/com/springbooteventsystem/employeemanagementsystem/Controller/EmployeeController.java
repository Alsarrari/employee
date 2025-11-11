package com.Spring_Boot.Employee.Controller;

import com.Spring_Boot.Employee.Api.ApiResponse;
import com.Spring_Boot.Employee.Model.Employee;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private ArrayList<Employee> employees = new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity<?> getAllEmployees() {
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody @Valid Employee employee) {
        employees.add(employee);
        return ResponseEntity.ok("add  employee");
    }

    @PutMapping("update/{index}")
    private ResponseEntity<?> upDate(@PathVariable int index, @RequestBody @Valid Employee employee, Errors errors) {
        if (index >= employees.size()) {
            return ResponseEntity.status(400).body("employee not found ");

        } else if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        employees.set(index, employee);
        return ResponseEntity.ok("employee update");
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> deleteEmployee(@PathVariable @Valid int id, Employee employee) {
        if (id > employees.size()) {
            return ResponseEntity.status(400).body("employee not found");
        }
        employees.remove(id);
        return ResponseEntity.ok("employee delete");
    }

    @GetMapping("/search/{position}")
    public ResponseEntity<?> searchByPosition(@PathVariable @Valid String position) {
        if (!position.equalsIgnoreCase("supervisor") && !position.equalsIgnoreCase("coordinator")) {
            return ResponseEntity.status(400).body(" Invalid position. Use 'supervisor' or 'coordinator'");
        }
        ArrayList<Employee> result = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getPosition().equalsIgnoreCase(position)) {
                result.add(emp);
            }
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/age/range")
    public ArrayList<Employee> getByAgeRange(@RequestBody @Valid int min, int max) {
        ArrayList<Employee> employees1 = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getAge() >= min && emp.getAge() <= max) {
                employees1.add(emp);
            }
        }
        return employees1;

    }


    @PutMapping("/apply/{id}")
    public ResponseEntity<?> appLeave(@PathVariable String id) {
        for (Employee emp : employees) {
            if (emp.getId().equals(id)) {
                if (emp.getOnLeave()) {
                    return ResponseEntity.status(400).body("employee on leave ");
                }
                return ResponseEntity.ok("employee leave applied");
            }
        }
        return ResponseEntity.status(404).body("employee not found");
    }

    @GetMapping("/noLeave")
    public ArrayList<Employee> noAnnualLeave() {
        ArrayList<Employee> employees1 = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getAnnualLeave() == 0) {
                employees1.add(emp);
            }
        }
        return employees1;
    }

    @PutMapping("/promote/{id}")
    public ResponseEntity<?> prompteEmployee(String id, String requesterId) {
        Employee request = null;
        Employee target = null;

        for (Employee emp : employees) {
            if (emp.getId().equals(requesterId)) {
                request = emp;
            }
            if (emp.getId().equals(id)) {
                target = emp;

            }
            target.setPosition("supervisor");

        }
return ResponseEntity.badRequest().body("");
    }


}










