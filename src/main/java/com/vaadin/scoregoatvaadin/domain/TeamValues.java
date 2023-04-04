package com.vaadin.scoregoatvaadin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TeamValues {
  BLACK("BLACK"),
  SILVER("silver"),
  MAIN_COLOR("#465a64"),
  DARK_SALMON("DarkSalmon"),
  WHITE("WHITE"),
  LIGHT_GREY("LightGrey"),
  LIGHT_BLUE("LightBlue"),
  RED("RED"),
  ORANGE("ORANGE"),
  DARK_GRAY("#484f4f"),
  EM_3("3em"),
  EM_4("4em"),
  EM_5("5em"),
  EM_6("6em"),
  EM_7("7em"),
  EM_8("8em"),
  EM_12("12em"),
  EM_14("14em"),
  EM_16("16em"),
  EM_19("19em"),
  EM_20("20em"),
  EM_35("35em"),
  EM_38("38em"),
  EM_39("39em"),
  EM_40("40em"),
  PX_14("14px"),
  PX_18("18px"),
  PX_24("24px"),
  PX_32("32px"),
  PX_36("36px"),
  BOLD("Bold"),
  POINTER("pointer");

  private final String values;
}
