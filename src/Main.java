import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Main {
    private static ArrayList<Profile> profiles = new ArrayList<Profile>(); // создаем коллекцию профилей людишек
    private static final File f = new File("profiles" + ".ser"); //заранее создадим файл, в который будем класть данные

    public static void main(String[] args) {
        if (f.exists()) {
            profiles = (ArrayList<Profile>) deserData("profiles");
        } //проверим наличие файла. Если он уже есть - десериализуем данные
        System.out.println(profiles.size());
        Profile profile = new Profile(); // создадим отдельный профиль чувака, потом попросим ввести данные
        profile.setName(JOptionPane.showInputDialog(null, "Enter name"));
        profile.setSurname(JOptionPane.showInputDialog(null, "Enter surname"));

        profiles.add(profile); //запишем его данные в коллекцию

        for (Profile p : profiles) { // с помощью цикла foreach выведем имечко
            System.out.println(p.getName() + " " + p.getSurname());
        }
        System.out.println(profiles.size());
        serData("profiles", profiles); // сериализуем
    }

    private static void serData(String filename, Object obj) { //метод, сериализующий данные
        try {
            FileOutputStream fos = new FileOutputStream(filename + ".ser"); // исходящий в файл поток
            ObjectOutputStream oos = new ObjectOutputStream(fos); // исходящий поток данных объекта
            oos.writeObject(obj); // передаем объект в поток, записываем в файл
            fos.close(); //закрываем потоки
            oos.close();
        } catch (FileNotFoundException e) { //ловим ошибки
            System.out.println("File not found");
            //e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("IO_error");
            //e.printStackTrace();
            System.exit(2);
        }
    }

    private static Object deserData(String filename) { //десериализующий метод. Тут все обратно
        Object retObject = null; //возвращаемый объект типа Object
        try {
            FileInputStream fis = new FileInputStream(filename + ".ser"); //входящий поток из файла
            ObjectInputStream ois = new ObjectInputStream(fis); // входящий поток данных объекта
            retObject = ois.readObject(); //запись данных в возвращаемый объект
            fis.close();//закрытие потоков
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            //e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("IO_error");
            //e.printStackTrace();
            System.exit(2);
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            System.out.println("Class not found");
            System.exit(3);
        }
        return retObject;
    }
}
