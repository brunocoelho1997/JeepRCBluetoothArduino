#ifndef Jeep_h
#define Jeep_h

#include "Arduino.h"

class Jeep
{
  public:
    Jeep();
    boolean selectGearSelected(int newGearSelected);
    boolean throttle(int percentage);
    boolean steering(int steeringSide);
    String toString();
  private:
    int actualGearSelected; //may be a D (forward) or R (reverse)
    
};

#endif
