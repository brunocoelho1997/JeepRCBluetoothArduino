#ifndef Utils_h
#define Utils_h

//constants

//types of gear
const int highGear = 1;
const int lowGear = 2;

//selected gear
const int dGear= 3;
const int rGear = 4;

//turn left/right
const int steeringLeft = 5;
const int steeringRight = 6;
const int steeringSpeed = 200;

const int throttle = 7;




// Motor A - motor which move forward and backward
const int enA = 9;
const int in1 = 8;
const int in2 = 7;
 
// Motor B - motor which change direction to left/right
const int enB = 3;
const int in3 = 5;
const int in4 = 4;

//delimiter of commands
const String commandDelimiter = "_";

/*
 * ---FONTS----
 * 
 * Class and file management:
 * https://www.arduino.cc/en/Hacking/LibraryTutorial
 * https://stackoverflow.com/questions/4705790/keeping-all-libraries-in-the-arduino-sketch-directory
 * 
 * 
 * Electrical schematic
 * https://dronebotworkshop.com/dc-motors-l298n-h-bridge/
 */
#endif
