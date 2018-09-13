package user.entity;

public class ResultVo<T> {
 T data;
 String code = "99999";
 String message = "报错";
public T getData() {
	return data;
}
public void setData(T data) {
	this.data = data;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
 
}
