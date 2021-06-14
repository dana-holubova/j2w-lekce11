package cz.czechitas.java2webapps.lekce11.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Entita reprezentující jendu knihu v knihovně.
 */
@Entity
public class Kniha {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String nazev;

  @NotBlank
  private String autor;

  @Min(1447)
  private short rokVydani;

  @Pattern(regexp = "[0-9]{13}|[0-9]{9}[0-9X]")
  private String isbn;

  /**
   * Sloupeček v databázi pro verzování. Stačí přidat @Version. O zbytek se postará Hibernate.
   * Spring a Hibernate mají pro verzování podporu. Stačí mít v databázi sloupeček, který bude řešit toto verzování. Je
   * jedno, jak bude pojmenovaný. V Hibernate je anotace @Version, která se dá nad tento sloupeček. Když se zakládá nový
   * záznam, tak sem Hibernate uloží 1. Když jej načte, tak načte verzi, která je z databáze. Když chce záznam uložit,
   * tak zkontroluje, že v databázi je stejná verze, jako má uživatel, který data odesílá. Když ano, tak číslo verze
   * zvýší o 1, když ne, tak vrátí chybu, že došlo ke konfliktu. Verze může být číselná, kdy se přičítá 1. Může tam být
   * i datum a čas, kdy se ukládá informace o tom, kdy ke změně došlo.
   */
  @Version
  private int version;

    /**
     * Sloupec stornováno slouží jako logický výmaz dat z databáze.
     */
  private boolean stornovano;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNazev() {
    return nazev;
  }

  public void setNazev(String nazev) {
    this.nazev = nazev;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public short getRokVydani() {
    return rokVydani;
  }

  public void setRokVydani(short rokVydani) {
    this.rokVydani = rokVydani;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public boolean isStornovano() {
    return stornovano;
  }

  public void setStornovano(boolean stornovano) {
    this.stornovano = stornovano;
  }
}
