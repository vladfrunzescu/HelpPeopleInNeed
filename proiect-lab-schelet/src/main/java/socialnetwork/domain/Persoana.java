package socialnetwork.domain;

import socialnetwork.utils.Type;

import java.util.Objects;

public class Persoana extends Entity<Long>{
    private String nume;
    private String prenume;
    private String username;
    private String parola;
    private Type oras;
    private String strada;
    private String numarStrada;
    private String telefon;

    public Persoana(String nume, String prenume, String username, String parola, Type oras, String strada, String telefon, String numarStrada) {
        this.setId(generateRandomId());
        this.nume = nume;
        this.prenume = prenume;
        this.username = username;
        this.parola = parola;
        this.oras = oras;
        this.strada = strada;
        this.telefon = telefon;
        this.numarStrada = numarStrada;
    }

    public Persoana(Long id, String nume, String prenume, String username, String parola, Type oras, String strada, String telefon, String numarStrada) {
        this.setId(id);
        this.nume = nume;
        this.prenume = prenume;
        this.username = username;
        this.parola = parola;
        this.oras = oras;
        this.strada = strada;
        this.telefon = telefon;
        this.numarStrada = numarStrada;
    }

    private Long generateRandomId() {
        long leftLimit = 1L;
        long rightLimit = 1000000000L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public Type getOras() {
        return oras;
    }

    public void setOras(Type oras) {
        this.oras = oras;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getNumarStrada() {
        return numarStrada;
    }

    public void setNumarStrada(String numarStrada) {
        this.numarStrada = numarStrada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persoana persoana = (Persoana) o;
        return Objects.equals(nume, persoana.nume) &&
                Objects.equals(prenume, persoana.prenume) &&
                Objects.equals(username, persoana.username) &&
                Objects.equals(parola, persoana.parola) &&
                oras == persoana.oras &&
                Objects.equals(strada, persoana.strada) &&
                Objects.equals(telefon, persoana.telefon) &&
                Objects.equals(persoana.getId(), this.getId()) &&
                Objects.equals(persoana.getNumarStrada(), this.getNumarStrada());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), nume, prenume, username, parola, oras, strada, telefon, this.getNumarStrada());
    }
}
