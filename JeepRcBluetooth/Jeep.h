#ifndef Jeep_h
#define Jeep_h

#include "Arduino.h"

class Jeep
{
  public:
    Jeep();
    boolean selectGearSelected(int newGearSelected);
    boolean selectGearType(int newGearType);
    boolean throttle(int percentage);
    boolean steering(int steeringSide);
    String toString();
  private:
    int actualGearSelected; //may be a D (forward) or R (reverse)
    int actualGearType; //may be a low or a high gear - if low gear the speed of the motor goes up to 50%. In high gear goes up to 100%
    
};

#endif
