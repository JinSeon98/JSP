package student.studentdto;

public class StudentDTO {
	String name;
	String value;
	int code;
	
	public StudentDTO() {
		
	}
	public StudentDTO(String name, String value, int code) {
		this.name = name;
		this.value = value;
		this.code = code;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public String getValue() {
		return value;
	}
	public int getCode() {
		return code;
	}
}
