package tr.edu.medipol.h5210017.domain;

/**
 * @file Party.java
 * @author enes
 * @description Party sınıfı isim ve oy sayısı değişkenleriyle oluşturuldu.
 * 				Dersde işlediğimiz örnek ve istediğiniz koşul kapsamında yetecek şekilde tasarlandı.
 */
public class Party {
    private String name; // Parti adını temsil eden değişken.
    private int voteCount; // Partinin oy sayısını temsil eden değişken.

    public Party(String name) {
        this.name = name;
        this.voteCount = 0;
    }

    public String getName() {
        return name;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void incrementVoteCount() {
        voteCount++; // Oy sayısını bir arttırır.
    }

    public void decrementVoteCount() {
        if (voteCount > 0) {
            voteCount--; // Oy sayısını bir azalt, ancak 0'dan küçük olamaz.
        }
    }
}
