package DZReflection;

public class Dog extends Animal{

    private String color;

    public Dog(String name, String age, String color) {
        super(name, age);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Dog " + super.toString();
    }
}
