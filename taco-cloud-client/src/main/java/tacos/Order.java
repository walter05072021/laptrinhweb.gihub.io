package tacos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

  

  private Long id;
  private Date placedAt;

  @NotEmpty(message="Delivery name is required")
  private String deliveryName;
  
  @NotEmpty(message="Street is required")
  private String deliveryStreet;
  
  @NotEmpty(message="City is required")
  private String deliveryCity;
  
  @NotEmpty(message="State is required")
  private String deliveryState;
  
  @NotEmpty(message="Zip code is required")
  private String deliveryZip;

//  @CreditCardNumber(message="Not a valid credit card number")
  private String ccNumber;
  
//  @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
//           message="Must be formatted MM/YY")
  private String ccExpiration;

//  @Digits(integer=3, fraction=0, message="Invalid CVV")
  private String ccCVV;

  private List<Taco> tacos = new ArrayList<>();
  
  public void addDesign(Taco design) {
    this.tacos.add(design);
  }
  

  
}
