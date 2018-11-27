export default {
  formatDate(value, parttern){
    if (value == null || !(value instanceof Date)) return value;
    if (!parttern) parttern = 'yyyy-MM-dd HH:mm:ss';
    return value.format(parttern);
  },
  formatNumber(value, precision, prefixSymbol, thousand, decimal){
    if (isNaN(value) || value === null) return value;
    if (!precision && isNaN(precision)) precision = 2;
    return value.format(precision, prefixSymbol, thousand, decimal);
  }
}
