#ifndef Jeep_h
#define Jeep_h

#include "Arduino.h"

class Jeep
{
  public:
    Jeep();
    boolean selectGear(int newGear);
    String toString();
  private:
    int actualGear;
};

#endif
