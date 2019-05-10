#include "Jeep.h"
#include "Utils.h"

Jeep::Jeep(){
  actualGear = lowGear;
}

/*
 * May select a low Gear or a high gear to the jeep. If it does successfully it will return true, otherwise returns false
 */
boolean Jeep::selectGear(int newGear)
{
  //if the gear is not recognized...
  if(newGear != hightGear && newGear != lowGear)
    return false;

  //if it's the same gear...
  if (newGear == actualGear)
    return false;

  actualGear = newGear;
}

String Jeep::toString()
{
  String str = "";
  str += "Jeep \nActual Gear: " + actualGear;
  
  return str;
}
