#ifndef Utils_h
#define Utils_h



/*
 * --------------------------------------------------
 * OBS
 * 
 * All commands are beetwen 1 ~265.
 * Specification:
 *  1- High Gear
 *  2- Low Gear
 *  3- D Gear
 *  4- R Gear
 *  5- Steer Left
 *  6- Steer Right
 *  10~265 - Throotle
 *  
 *  --------------------------------------------------
 */
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
const int steeringSpeed = 50;
const int timeoutSteering = 2000;

const int minThrottle = 10;
const int maxThrottle = 265; //since the limit of the pwm is 0~255

/*
 * AVAIABLE POSITIONS TO DEFINE COMMANDS- >=6 <= 9
 */




// Motor A - motor which move forward and backward
const int enA = 9;
const int in1 = 8;
const int in2 = 7;
 
// Motor B - motor which change direction to left/right
const int enB = 3;
const int in3 = 5;
const int in4 = 4;


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
