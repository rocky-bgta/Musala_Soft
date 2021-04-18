export default {
  isEmptyObject(object){
    let isObjectEmpty=true;
    if(object!=null || object != undefined){
      for (let key in object) {
        if (object[key] !== "") {
          isObjectEmpty=false;
          break;
        }
      }
    }
    return isObjectEmpty;
  },
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
