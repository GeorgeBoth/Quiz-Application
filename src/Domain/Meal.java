package Domain;

import java.util.List;
import java.util.ArrayList;

public class Meal {
    String name;
    int time;

    String ingredients;

    public Meal(String name, int time, String ingredients) {
        this.name = name;
        this.time = time;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", time=" + time +
                ", ingredients=" + ingredients +
                '}';
    }
}
