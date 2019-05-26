#ifndef Utils_h
#define Utils_h



/*
 * --------------------------------------------------
 * OBS
 * 
 * All commands are beetwen 1 ~265.
 * Specification:
 *  1- D Gear
 *  2- R Gear
 *  3- Steer Left
 *  4- Steer Right
 *  10~110 - Throotle
 *  
 *  --------------------------------------------------
 */
//constants

//selected gear
const int dGear= 1;
const int rGear = 2;

//turn left/right
const int steeringLeft = 3;
const int steeringRight = 4;
const int steeringSpeed = 255;
const int timeoutSteering = 2000;

const int minThrottle = 10;
const int maxThrottle = 110; //the range of the throotle is 0~100

const int minPwmOfMotor = 100; //the range of pwm will be 100~255


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
