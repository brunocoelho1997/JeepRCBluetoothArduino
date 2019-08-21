#include "Jeep.h"
#include "Utils.h"

  Jeep::Jeep(){
    actualGearSelected = dGear;
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

    return true;
  }

 /*
 * This method is called when the user throttle. The method receive a percentage of how much the user is pressing the throttle.
 */
  boolean Jeep::throttle(int value)
  {

    //if the percentage received is not valid...
    if(value< minThrottle || value > maxThrottle)
      return false;
      
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
    
    if(value == 10) //means to stop throotling
    {
      analogWrite(enA, 0);
      //Serial.println("Stopped the motor.");
      return true;
    }

    
    //a tmp var as float just as auxiliary for the calculus. To define the speed of the motor we need to define the PWM in a range 0~255
    //since commands between 1~10 are steering, gears etc we need to decrement 10
    float tmp = value - 10;
  
    tmp = minPwmOfMotor + (155 * tmp/100);
    
    int pwm = (int) tmp;
    analogWrite(enA, pwm);

    //Serial.println("pwm:" + String(pwm));

    return true;
  }

  /*
  * This method is called when the user is steering the car
  */
  boolean Jeep::steering(int steeringSide)
  {
    //if the percentage received is not valid...
    if(steeringSide != steeringLeft && steeringSide != steeringRight)
      return false;

    
     
    if(steeringSide == steeringLeft)
    {
      digitalWrite(in3, HIGH);
      digitalWrite(in4, LOW);
      //Serial.println("Jeep: Steering left");

      delay(timeoutSteering);
      digitalWrite(in3, LOW);
      digitalWrite(in4, LOW);
    }
    else
    {
      digitalWrite(in3, LOW);
      digitalWrite(in4, HIGH);
      //Serial.println("Jeep: Steering right");

      delay(timeoutSteering);
      digitalWrite(in3, LOW);
      digitalWrite(in4, LOW);
    }

    analogWrite(enB, steeringSpeed);

    return true;
  }
  
  String Jeep::toString()
  {
    String str = "";
    str += "Actual Gear Selected: " + String(actualGearSelected) + "\n";
    
    return str;
  }
