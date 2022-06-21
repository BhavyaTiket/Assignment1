package Bhavya.Assignment1.document;


import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Document(collection = "project")
public class Projects {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;

    private String description;

    private String monthOfCompletion;

    private String email;

    public Projects(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMonthOfCompletion() {
        return monthOfCompletion;
    }

    public void setMonthOfCompletion(String monthOfCompletion) {
        this.monthOfCompletion = monthOfCompletion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Projects{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", monthOfCompletion='" + monthOfCompletion + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
