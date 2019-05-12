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

  // send data to serial only when you receive data:
  if (Serial.available() > 0) {

    String command = Serial.readString();

    processCommand(command);

    Serial.println(jeep.toString());
  }

}

boolean processCommand(String commandTmp)
{
  int command, value;

  //validations...
  if (commandTmp == NULL || commandTmp.length() == 0)
    return false;

  //split the command to an array
  String *commandArray = getCommandAsArray(commandTmp);

  //validations...
  if (commandArray == NULL)
    return false;

  //get the command (possible examples: 1, 2, 3)
  command = commandArray[0].toInt();

  //if exist a second value...
  if(commandArray[1] != NULL && commandArray[1].length() != 0)  
    value = commandArray[1].toInt();


  switch(command)
  {
    case highGear:
      jeep.selectGearType(highGear);
    break;
    case lowGear:
      jeep.selectGearType(lowGear);
    break;
    case throttle:
      jeep.throttle(value);
      value = 0;
    break;

    //TODO: process the other commands
  }

}

/*
 * split the received command to an array of 2 positions. The first one indicates the command and the second (if exists) indicates a value (percentage of throttle, etc)
 * An possible example of command are: 
 *  - 7_50 (throttle as 50%)
 *  - 5 (turn left)
 *  - 2 (select low gear)
 */
String * getCommandAsArray(String commandTmp)
{
  String commandArray[2];

  String token;
  size_t pos = 0;

  pos = commandTmp.indexOf(commandDelimiter);

  //if does not exist '_' in received command
  if (pos == -1)
  {
    commandArray[0] = commandTmp;
    return commandArray;
  }

  String command = commandTmp.substring(0, pos);
  commandArray[0] = command;
  //get value received in command
  commandArray[1] = commandTmp.substring(pos + commandDelimiter.length(), commandTmp.length() - 1);

  return commandArray;
}
