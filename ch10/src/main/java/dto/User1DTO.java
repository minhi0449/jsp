package dto;

public class User1DTO {
	
	
	private String uid;
	private String name;
	private String birth;
	private String hp;
	private int age;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public int getAge() {
		return age;
	}
	// int age
	public void setAge(int age) {
		this.age = age;
	}
	// 오버로딩 메서드
	// null 들어오면 에러니까 null 체크하고 가면 좋음 프로그램 안정성
	public void setAge(String age) {
		if(age != null){
			this.age = Integer.parseInt(age); 
		}else {
			this.age = 0;
		}
		
	}
	
	@Override
	public String toString() {
		return "User1DTO [uid=" + uid + ", name=" + name + ", birth=" + birth + ", hp=" + hp + ", age=" + age + "]";
	}
	
}
