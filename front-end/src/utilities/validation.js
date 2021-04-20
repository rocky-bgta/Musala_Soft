export default {
  isAllPropertyOfObjectContainValue(object){
    let allPropertyHasValue=true;
    if(object!=null || object != undefined) {
      for (let key in object) {
        if (object[key] === "") {
          allPropertyHasValue = false;
          break;
        }
      }
      return allPropertyHasValue;
    }
  }
}
