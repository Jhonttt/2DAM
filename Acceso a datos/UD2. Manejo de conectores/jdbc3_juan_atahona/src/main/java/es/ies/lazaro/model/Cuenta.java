package es.ies.lazaro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class Cuenta {
  private int id;
  private String titular;
  private double saldo;
}
