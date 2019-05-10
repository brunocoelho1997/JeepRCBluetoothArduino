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
  
  String Jeep::toString()
  {
    String str = "";
    str += "Jeep \nActual Gear Selected: " + actualGearSelected;
    str += "Jeep \nActual Gear Type: " + actualGearType;
    
    
    return str;
  }
