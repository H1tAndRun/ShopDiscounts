import java.util.Objects;

public class Buyer {
     private  String name;
     private double exp;

    public Buyer(String name, double exp) {
        this.name = name;
        this.exp = exp;
    }

    public String getName() {
        return name;
    }

    public double getExp() {
        return exp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }


}
