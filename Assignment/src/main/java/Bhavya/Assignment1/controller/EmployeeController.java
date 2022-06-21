package Bhavya.Assignment1.controller;

import Bhavya.Assignment1.model.EmployeeModel;
import Bhavya.Assignment1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "addEmployee", method = RequestMethod.POST)
    public  String addEmployee(@RequestBody EmployeeModel employeeModel)
    {
        return employeeService.addEmployee(employeeModel);

    }

    @RequestMapping(value="readEmployee", method = RequestMethod.GET)
    public List<EmployeeModel> readEmployee(){
        return employeeService.readEmployee();
    }

    @RequestMapping(value = "updateEmployee", method = RequestMethod.PUT)
    public String updateEmployee(@RequestBody EmployeeModel employeeModel) {
        return employeeService.updateEmployee(employeeModel);
    }

    @RequestMapping(value = "deleteEmployee",method = RequestMethod.DELETE)
    public String deleteEmployee(@RequestBody EmployeeModel employeeModel){
        return employeeService.deleteEmployee(employeeModel);
    }
}
