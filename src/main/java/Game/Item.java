package Game;

public abstract class Item {
    protected String name;
    protected String description;
    protected ItemGrade grade;

    public Item(String name, String description, ItemGrade grade) {
        this.name = name;
        this.description = description;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemGrade getGrade() {
        return grade;
    }

    public abstract void use(Character character);
} 