export default {
	formatDate(value, parttern){
		if(value == null || !(value instanceof Date)) return value;
		if(!parttern) parttern = 'yyyy-MM-dd HH:mm:ss';
		return value.format(parttern);
	}
}