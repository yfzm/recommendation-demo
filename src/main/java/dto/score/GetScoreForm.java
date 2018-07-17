package dto.score;

public class GetScoreForm {
    ClothAttributes upper;
    ClothAttributes lower;

    public ClothAttributes getUpper() {
        return upper;
    }

    public void setUpper(ClothAttributes upper) {
        this.upper = upper;
    }

    public ClothAttributes getLower() {
        return lower;
    }

    public void setLower(ClothAttributes lower) {
        this.lower = lower;
    }
}
