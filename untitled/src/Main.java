import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<Human,String> map = new HashMap<>(16,0.5f);
//        map.put("a","1234");
        Human h = new Human("Vasya");
        map.put(h,"1234");
        h.name = "Vasyatka";
//        map.put("b","678");
//        map.put("c","555");
//        map.put(null,"555");
//        map.put("s","555");
//        map.put("aa","555");
//        map.put("sss","555");
//        map.put("ssss","555");
//        map.put("sssss","555");
//        System.out.println(map);
        Field f = HashMap.class.getDeclaredField("table");
        f.setAccessible(true);
        Object[] obs = (Object[]) f.get(map);
//        System.out.println(obs.length);
//        Arrays.stream(obs).forEach(System.out::println);

        System.out.println("Phones: "+  map);
        System.out.println(map.get(h));
    }


}

class Human{
    String name;

    Human(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Human human = (Human) o;
        return Objects.equals(name, human.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                '}';
    }
}
