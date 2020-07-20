package DTO;

public class PersonDTO {
    private String id;
    private String name;
    private String color;
    private int retired;

    public PersonDTO() {
    }

    public PersonDTO(String id, String name, String color, int retired) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.retired = retired;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getRetired() {
        return retired;
    }

    public void setRetired(int retired) {
        this.retired = retired;
    }
}
