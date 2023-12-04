package DZReflection;

public class Cat extends Animal{

    private String voice;

    public Cat(String name, String age) {
        super(name, age);
        voice = "мау";
    }

    public String getVoice() {
        return voice;
    }
    public void makeSound(){
        System.out.printf("это животное издает звук %s\n", voice);
    }

    @Override
    public String toString() {
        return "Cat " + super.toString();
    }
}
