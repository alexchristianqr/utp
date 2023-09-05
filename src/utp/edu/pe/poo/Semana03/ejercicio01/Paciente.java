
package utp.edu.pe.poo.Semana03.ejercicio01;

public class Paciente {
    private int codigo;
    private String nombres;
    private String apellidos;
    private int edad;
    private double peso;
    private double talla;
    private String sexo;
    private double costoxDia;
    private int diasDeInternamiento;
    private String especialidad;
    
    //constructor

    public Paciente(int codigo, String nombres,String apellidos, int edad, double peso, double talla, String sexo, double costoxDia, int diasDeInternamiento, String especialidad) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;       
        this.edad = edad;
        this.peso = peso;
        this.talla = talla;
        this.sexo = sexo;
        this.costoxDia = costoxDia;
        this.diasDeInternamiento = diasDeInternamiento;
        this.especialidad = especialidad;
    }
    //métodos
    
    //método costo base
    public double costoBase(){
    
     return diasDeInternamiento*costoxDia;
    }
    //método costo adicional por laboratorio
    public double costoAdicionalLaboratorio(){
        if(especialidad.equalsIgnoreCase("cirugía"))
           return 300; 
        else
            return 0;     
    }
    //método costo adicional equipos
    
    public double costoAdicionalEquipos(){
        if(especialidad.equalsIgnoreCase("cardiología"))
         return 480;  
        else
            return 0;
    }
   //método para costo total
    
    public double costoTotal(){
    
        return costoBase()+costoAdicionalLaboratorio()+costoAdicionalEquipos();
    }
    
    //métodos getter y setters
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getCostoxDia() {
        return costoxDia;
    }

    public void setCostoxDia(double costoxDia) {
        this.costoxDia = costoxDia;
    }

    public int getDiasDeInternamiento() {
        return diasDeInternamiento;
    }

    public void setDiasDeInternamiento(int diasDeInternamiento) {
        this.diasDeInternamiento = diasDeInternamiento;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    
}
