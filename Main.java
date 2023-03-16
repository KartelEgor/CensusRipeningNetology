import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        //--------------------------Кол-во совершеннолетних------------------------------------
        long adultCount = persons.stream()
                .filter(x -> x.getAge() > 18)
                .count();
        System.out.println(adultCount);

        //--------------------------Призывники-------------------------------------------------
        List<String> conscript = persons.stream()
                .filter(x -> x.getSex().equals(Sex.MAN))
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(conscript);

        //---------------------------Работоспособные-------------------------------------------
        List<String> workable = persons.stream()
                .filter(x -> x.getSex().equals(Sex.MAN) && x.getAge() >= 18 && x.getAge() < 65
                        || x.getSex().equals(Sex.WOMAN) && x.getAge() >= 18 && x.getAge() < 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(workable);

    }
}