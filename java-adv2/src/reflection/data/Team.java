package reflection.data;

public class Team {

    private String id;
    private String name;

    public Team() {
    }

    public Team(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Team{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}
