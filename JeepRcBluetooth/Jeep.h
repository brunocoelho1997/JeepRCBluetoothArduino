#ifndef Jeep_h
#define Jeep_h

#include "Arduino.h"

class Jeep
{
  public:
    Jeep();
    boolean selectGearSelected(int newGearSelected);
    boolean selectGearType(int newGearType);
    String toString();
  private:
    int actualGearSelected; //may be a D (forward) or R (reverse)
    int actualGearType; //may be a low or a high gear
    
};

#endif
