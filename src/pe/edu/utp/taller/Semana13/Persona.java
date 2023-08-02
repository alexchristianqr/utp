package pe.edu.utp.taller.Semana13;

public class Persona {

    public String nombre;
    public String apellidoMaterno;
    public String apellidoPaterno;
    public String sexo;
    public String edad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    void comer() {
        System.out.println("La persona esta comiendo...");
    }

    void beber() {
        System.out.println("La persona esta bebiendo...");
    }

    void dormir() {
        System.out.println("La persona esta durmiendo...");
    }
}
