import java.io.Serializable;

public class Profile implements Serializable{ //указываем jav-e, что этот класс (а точнее его экземпляры) могут быть сериализованы

    private String name;
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
