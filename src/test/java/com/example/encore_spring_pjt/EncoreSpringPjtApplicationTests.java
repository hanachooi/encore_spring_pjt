package com.example.encore_spring_pjt;

// import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class EncoreSpringPjtApplicationTests {

	@Autowired
	private ApplicationContext context;

	// sessoin, 즉 cp를 관리하는 Factory
	// 이 cp는 orm에서 사용
	// SqlSessionFactory (DI), 객체생성 없이 의존성 주입
	@Autowired
	// private SqlSessionFactory sessionFactory;

	@Test
	void contextLoads(){
		
	}

	@Test
	public void testContext(){
		
		// Dependency Lookup : getBean()
		try {

			System.out.println(">>> debug");
			System.out.println(context.getBean("encore"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
