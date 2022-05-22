package enums;

public enum Roles {
    ADMINISTRATOR("administrator"),
    INSTRUCTOR("instructor"),
    STUDENT("student");

    private String name;

    private Roles(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return Long.valueOf(ordinal());
    }
}
