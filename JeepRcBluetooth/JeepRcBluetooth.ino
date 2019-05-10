#include "Jeep.h"
#include "Utils.h"

Jeep jeep;

void setup() {

  Serial.begin(9600);

   // Set all the motor control pins to outputs
  pinMode(enA, OUTPUT);
  pinMode(enB, OUTPUT);
  pinMode(in1, OUTPUT);
  pinMode(in2, OUTPUT);
  pinMode(in3, OUTPUT);
  pinMode(in4, OUTPUT);
}

void loop() {

  Serial.print(jeep.toString());

  
}
