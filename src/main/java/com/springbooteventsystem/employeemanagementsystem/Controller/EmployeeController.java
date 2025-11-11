package com.springbooteventsystem.employeemanagementsystem.Controller;

import com.springbooteventsystem.employeemanagementsystem.Api.ApiResponse;
import com.springbooteventsystem.employeemanagementsystem.Model.Employee;
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
    public ResponseEntity getAllEmployees() {
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/add")
    public ResponseEntity addEmployee(@RequestBody @Valid Employee employee) {
        employees.add(employee);
        return ResponseEntity.ok("add  employee");
    }

    @PutMapping("update/{index}")
    private ResponseEntity upDate(@PathVariable int index, @RequestBody @Valid Employee employee , Errors errors) {
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
    private ResponseEntity deleteEmployee(@PathVariable @Valid int id , Employee employee){
      if (id>employees.size()){
          return ResponseEntity.status(400).body("employee not found");
      }
        employees.remove(id);
      return ResponseEntity.ok("employee delete");
    }
//    @GetMapping("/search/{id}")
//    public ResponseEntity search(@PathVariable @Valid String postion){
//        if (postion.equalsIgnoreCase("superVisior")&&postion.equalsIgnoreCase("coordinator"));
//    }


}

