package es.ies.lazaro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoXML {
  private String nombre;
  private int edad;
  private String ciclo;
}
