package Model;

public class Person {
    private String id;
    private String name;
    private String color;
    private int retired;

    public Person() {
    }

    public Person(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Person(String id, String name, String color, int retired) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.retired = retired;
    }

    public int getRetired() {
        return retired;
    }

    public void setRetired(int retired) {
        this.retired = retired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
