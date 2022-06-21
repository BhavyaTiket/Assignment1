package Bhavya.Assignment1.service;

import Bhavya.Assignment1.document.Projects;
import Bhavya.Assignment1.entity.Employee;
import Bhavya.Assignment1.model.EmployeeModel;
import Bhavya.Assignment1.model.ProjectModel;
import Bhavya.Assignment1.repository.EmployeeRepository;
import Bhavya.Assignment1.repository.ProjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional
    public String  addEmployee(EmployeeModel employeeModel){
        if (!employeeRepository.existsByEmail(employeeModel.getEmail())){
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeModel, employee);
            try {
                employeeRepository.save(employee);
                employeeModel.getProjects().stream().forEach(c -> {
                    Projects project = new Projects();
                    c.setEmail(employeeModel.getEmail());
                    BeanUtils.copyProperties(c, project);
                    try {
                        projectRepository.save(project);
                    }catch (Exception e){
                        throw e;
                    }

                });
            }catch (Exception e){
                throw e;
            }

            return "Resource added successfully!";
        }else {
            return "Duplicate resource";
        }
    }

    public List<EmployeeModel> readEmployee(){
        List<EmployeeModel> employees = new ArrayList<>();
        List<Employee> employeeList = new ArrayList<>();
        try {
            employeeList = employeeRepository.findAll(); //Fetch all the Students from the database.
        }catch (Exception e){
            throw e;
        }

        if (employeeList.size() > 0){ //If the above list is not empty then return the list after unwrapping all the records
            employeeList.stream().forEach(s -> { //Traverse through the reords
                EmployeeModel employeeModel = new EmployeeModel();
                employeeModel.setFirstName(s.getFirstName());
                employeeModel.setLastName(s.getLastName());
                employeeModel.setEmail(s.getEmail());
                employeeModel.setDepartmentName(s.getDepartmentName());
                employeeModel.setEmploymentType(s.getEmploymentType());
                List<Projects> projectList = new ArrayList<>();
                List<ProjectModel> projects = new ArrayList<>();
                try {
                    projectList = projectRepository.findProjectByEmail(s.getEmail()); //Fetch all the courses by email ID.
                }catch (Exception e){
                    throw e;
                }
                if (projectList.size() > 0){
                    projectList.stream().forEach(c -> {
                        ProjectModel projectModel = new ProjectModel();
                        BeanUtils.copyProperties(c, projectModel);
                        projects.add(projectModel);
                    });
                }
                employeeModel.setProjects(projects);
                employees.add(employeeModel);
            });
        }
        return employees;
    }

    @Transactional
    public String updateEmployee(EmployeeModel employeeModel){
        if (employeeRepository.existsByEmail(employeeModel.getEmail())){ //Check for availability of resource by email ID. Update if resource exists.
            Employee employee = employeeRepository.findByEmail(employeeModel.getEmail()).get(0);
            BeanUtils.copyProperties(employeeModel, employee);
            try {
                employeeRepository.save(employee); //Update Student
                List<Projects> projects = projectRepository.findProjectByEmail(employeeModel.getEmail()); //Read the courses from the database
                for (int i = 0; i < employeeModel.getProjects().size(); i++){
                    BeanUtils.copyProperties(employeeModel.getProjects().get(i),projects.get(i));
                }

                projects.stream().forEach(c -> {
                    Projects project = projectRepository.findById(c.getId()).get();
                    BeanUtils.copyProperties(c, project);
                    project.setEmail(employeeModel.getEmail());
                    projectRepository.save(project);
                });

            }catch (Exception e){
                throw e;
            }
            return "Resource was updated successfully";
        }else{
            return "Resource does not exist";
        }
    }

    @Transactional
    public String deleteEmployee(EmployeeModel employeeModel){
        if (employeeRepository.existsByEmail(employeeModel.getEmail())){
            try {
                employeeRepository.deleteByEmail(employeeModel.getEmail());
                try {
                    projectRepository.deleteByEmail(employeeModel.getEmail());
                }catch (Exception e){
                    throw e;
                }
            }catch (Exception e){
                throw e;
            }
            return "Removed successfully!";
        }else {
            return "Resource does not exist.";
        }
    }


}
