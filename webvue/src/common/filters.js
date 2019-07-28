export default {
  formatDate(value, parttern){
    if(!value || value == 0 || !(value instanceof Date || !isNaN(value))) return value;
    if(!isNaN(value)) {
        value = new Date(value);
    }
    if (!parttern) parttern = 'yyyy-MM-dd HH:mm:ss';
    return value.format(parttern);
  },
  formatNumber(value, precision, prefixSymbol, thousand, decimal){
    if (isNaN(value) || value === null) return value;
    if (!precision && isNaN(precision)) precision = 2;
    return value.format(precision, prefixSymbol, thousand, decimal);
  },
  enumNameFilter(value, enumType){
    var enumIns = window[enumType];
    if (!enumType || !enumIns || !(enumIns instanceof Object)) return value;
    for(var ind in enumIns) {
      if(enumIns[ind] && enumIns[ind].key === value) return enumIns[ind].name;
    }
    return value;
  }
}
