package es.ies.lazaro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class Movimiento {
  private int id;
  private int origen;
  private double importe;
}
  