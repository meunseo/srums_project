package mybatis.common;

public class TestDto {
	
	private String id;
	private String name;
	private int age;
	public TestDto() {}
    public TestDto(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getter와 Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestDto {id='" + id + "', name='" + name + "', age=" + age + "}";
    }
}