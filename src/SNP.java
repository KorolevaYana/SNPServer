/**
 * Created by fox on 7/28/16.
 */
public class SNP {
    private int id;
    private String[] variants;

    SNP() {
        id = 0;
        variants = new String[2];
    }

    SNP(int id, String[] variants) {
        this.id = id;
        this.variants = variants;
    }

    int getId() {
        return id;
    }

    String[] getVariants() {
        return variants;
    }

    void setId(int id) {
        this.id = id;
    }

    void setVariants(String[] variants) {
        this.variants = variants;
    }
}
