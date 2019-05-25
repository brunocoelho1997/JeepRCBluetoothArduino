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

  Serial.println("Ready to read commands.");
  
}

void loop() {

  
  // send data to serial only when you receive data:
  if (Serial.available() > 0) {

    String command = Serial.readString();

    Serial.println("Command printed: " + command);

    boolean result = processCommand(command);

    if(result == false)
      Serial.println("\nError processing the command.");
    
    Serial.println("\nJeep printed:");
    Serial.println(jeep.toString());
  }

}

boolean processCommand(String commandTmp)
{
  int command, value;

  //validations...
  if (commandTmp == NULL || commandTmp.length() == 0)
    return false;

  command = commandTmp.toInt();
  
  switch(command)
  {
    case highGear:
      return jeep.selectGearType(highGear);
    break;
    case lowGear:
      return jeep.selectGearType(lowGear);
    break;
    
    case steeringLeft:
      return jeep.steering(steeringLeft);
    break;
    case steeringRight:
      return jeep.steering(steeringRight);
    break;
    case throttle:
      boolean result = jeep.throttle(value);
      value = 0;
      return result;
    break;
    
  }
}
