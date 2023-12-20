package DZSerialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;


/*
Урок 3. Сериализация
   1. Разработайте класс Student с полями String name,
       int age, transient double GPA (средний балл).
        Обеспечьте поддержку сериализации для этого класса.
        Создайте объект класса Student и инициализируйте его данными.
        Сериализуйте этот объект в файл. Десериализуйте объект обратно в программу из файла.
        Выведите все поля объекта, включая GPA, и обсудите,
        почему значение GPA не было сохранено/восстановлено.
   2. * Выполнить задачу 1 используя другие типы сериализаторов (в xml и json документы).
*/
public class Program {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String file = "JuniorDZ\\student.bin";
        String fileXml = "JuniorDZ\\student.xml";
        String fileJson = "JuniorDZ\\student.json";
        Student student = new Student("Dima", 12, 5.5);
        saveObject(student, file);
        serializedToJson(student, fileJson);
        serializedToXml(student, fileXml);
        System.out.printf("данные до сохранения в файл: %s\n", student.toString());
        student = (Student) loadObject(file);
        System.out.printf("данные после загрузки из файла: %s\n", student.toString());
       // saveJson(student);

    }


    private static void saveObject(Serializable student, String files) throws IOException {
        try (FileOutputStream output = new FileOutputStream(files);
             ObjectOutputStream ser = new ObjectOutputStream(output)){
            ser.writeObject(student);
            System.out.println("сохранение объекта в файл.");
        }
    }
    private static Object loadObject(String files) throws IOException, ClassNotFoundException {
        try(FileInputStream input = new FileInputStream(files);
        ObjectInputStream ser = new ObjectInputStream(input);){
            Object obj = ser.readObject();
            System.out.println("объект загружен из файла");
            return obj;
        }
    }
    private static void serializedToXml(Serializable ser, String files) throws IOException {
        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File(files), ser);
        System.out.println("сохранение в XML файл.");
    }
    private static void serializedToJson(Serializable ser, String files) throws IOException {
        ObjectMapper mapper = new JsonMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
         mapper.writeValue(new File(files), ser);
        System.out.println("сохранение в json файл.");
    }
    private static void saveJson(Serializable ser) throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        obj.enable(SerializationFeature.INDENT_OUTPUT);
        String str = obj.writeValueAsString(ser);
        System.out.println(str);
    }

}
