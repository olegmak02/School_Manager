package domain;

public class Student extends Entity {
    private String surname;
    private String name;
    private int studyYear;

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

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;

        Student student = (Student) o;

        if (studyYear != student.studyYear) return false;
        if (surname != null ? !surname.equals(student.surname) : student.surname != null) return false;
        return name != null ? name.equals(student.name) : student.name == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + studyYear;
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", studyYear=" + studyYear +
                '}';
    }
}
