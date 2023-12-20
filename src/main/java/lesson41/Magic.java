package lesson41;


import javax.persistence.*;


@Entity
@Table(name = "test.magic")
public class Magic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "names")
    private String name;

    @Column(name = "damages")
    private int damage;

    @Column(name = "atech")
    private int attBonus;

    @Column(name = "bron")
    private int db;

    public Magic( String name, int damage, int attBonus,int db) {
        this.name = name;
        this.damage = damage;
        this.attBonus = attBonus;
        this.db = db;
    }
    public Magic(){};

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getAttBonus() {
        return attBonus;
    }

    public int getDb() {
        return db;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setAttBonus(int attBonus) {
        this.attBonus = attBonus;
    }

    public void setDb(int db) {
        this.db = db;
    }

    @Override
    public String toString() {
        return "Magic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", damage=" + damage +
                ", attBonus=" + attBonus +
                ", db=" + db +
                '}';
    }

}
