package core.spring.singleton;

public class SingletonService {

	/**
	 * 자기 자신을 static으로 선언하면 클래스 레벨에 올라가므로 딱 하나의 인스턴스만 존재하게 됨.
	 *
	 * 1) static 영역에 객체를 딱 1개의 인스턴스만 생성
	 * 2) public 으로 열어서 객체 인스턴스 필요시, getInstance의 static 메서드로만 조회 가능
	 * 3) 생성자를 private으로 선언해서 외부에서 new 키워드로 객체 생성 못하게 설정
	 */
	private static final SingletonService instance = new SingletonService();

	public static SingletonService getInstance() {
		return instance;
	}

	// singleton Service 인스턴스를 private으로 하여 외부에서 생성못하게 막음.
	private SingletonService() {

	}

	public void logic() {
		System.out.println("싱글톤 객체 로직 호출");
	}

}
