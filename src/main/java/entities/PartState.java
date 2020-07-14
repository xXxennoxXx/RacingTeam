package entities;

public enum PartState {
    FINISHED("Finished"), UNDER_CONSTRUCTION("Under Construction");

    private String name;

    PartState(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
