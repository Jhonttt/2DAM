package ejercicio7;

import java.io.Serializable;

public class Libro implements Serializable {
  private static final long serialVersionUID = 1L;
  private String isbn;
  private String titulo;
  private String autor;
  private int anioPublicacion;
  private boolean prestado;

  public Libro(int anioPublicacion, String autor, String isbn, boolean prestado, String titulo) {
    this.anioPublicacion = anioPublicacion;
    this.autor = autor;
    this.isbn = isbn;
    this.prestado = prestado;
    this.titulo = titulo;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public int getAnioPublicacion() {
    return anioPublicacion;
  }

  public void setAnioPublicacion(int anioPublicacion) {
    this.anioPublicacion = anioPublicacion;
  }

  public boolean isPrestado() {
    return prestado;
  }

  public void setPrestado(boolean prestado) {
    this.prestado = prestado;
  }

  @Override
  public String toString() {
    return "Libro [isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", anioPublicacion=" + anioPublicacion
        + ", prestado=" + prestado + ", getIsbn()=" + getIsbn() + ", getTitulo()=" + getTitulo() + ", getAutor()="
        + getAutor() + ", getAnioPublicacion()=" + getAnioPublicacion() + ", isPrestado()=" + isPrestado()
        + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
  }

}
