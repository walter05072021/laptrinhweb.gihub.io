package tacos;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@NoArgsConstructor(force=true)

public class Ingredient {
  private final Long id;
  private final String name;
  private final Type type;
  public static enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Type getType() {
    return type;
  }
}
