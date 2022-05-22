package domain;


public class Instructor extends Entity {
    private String surname;
    private String name;

    public String getFullName() {
        return surname + " " + name;
    }
    
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + getId() +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
