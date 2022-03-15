package pl1111w.bean;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/3/15 10:43
 */
public class Pet {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                '}';
    }
}
