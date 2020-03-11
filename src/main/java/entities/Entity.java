package entities;

public class Entity<ID> {
    private ID id;
    public ID getId() {
        /**
         * Getter id
         * @param
         * @return id-ID, id ul entitatii
         */
        return id;
    }
    public void setId(ID id) {
        /**
         * Setter id
         * @param id-ID, id ul entitatii
         * @return
         */
        this.id = id;
    }
}
