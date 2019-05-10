#include "Jeep.h"
#include "Utils.h"

  Jeep::Jeep(){
    actualGearSelected = dGear;
    actualGearType = lowGear;
  }
  
  /*
   * May select a low Gear or a high gear to the jeep. If it does successfully it will return true, otherwise returns false
   */
  boolean Jeep::selectGearType(int newGearType)
  {
    //if the gear is not recognized...
    if(newGearType != hightGear && newGearType != lowGear)
      return false;
  
    //if it's the same gear...
    if (newGearType == actualGearType)
      return false;
  
    actualGearType = newGearType;
  }

  /*
   *  The user may select a gear. The gear selected may be a D (forward) or R (reverse)
   */
  boolean Jeep::selectGearSelected(int newGearSelected)
  {
    //if the selected gear received is not recognized...
    if(newGearSelected != dGear && newGearSelected != rGear)
      return false;
  
    //if it's the same gear...
    if (newGearSelected == actualGearSelected)
      return false;
  
    actualGearSelected = newGearSelected;
  }

 /*
 * This method is called when the user throttle. The method receive a percentage of how much the user is pressing the throttle.
 */
  boolean Jeep::throttle(int percentage)
  {
    /*
    *  initially defines the direction of the motors - if the car will go forward or backward
    */
    if(actualGearSelected == dGear)
    {
    digitalWrite(in1, HIGH);
    digitalWrite(in2, LOW);
    }
    else
    {
    digitalWrite(in1, LOW);
    digitalWrite(in2, HIGH);
    }
    
    /*
    *  after direction defines we must define the pulse width modulation (PWM) - speed of the motor.
    */

    //a tmp var as float just as auxiliary for the calculus. To define the speed of the motor we need to define the PWM in a range 0~255
    float tmp = 255 * percentage;

    //if the type of the gear selected is the lowGear, we need define the speed as the it's half
    if(actualGearType == lowGear)
      tmp = tmp * 0.5;
    
    int pwm = (int) tmp;
    analogWrite(enA, pwm);
    
  }
  
  String Jeep::toString()
  {
    String str = "";
    str += "Jeep \nActual Gear Selected: " + actualGearSelected;
    str += "Jeep \nActual Gear Type: " + actualGearType;
    
    
    return str;
  }
