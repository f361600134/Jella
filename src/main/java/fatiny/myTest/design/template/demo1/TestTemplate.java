package fatiny.myTest.design.template.demo1;

import org.junit.Test;

public class TestTemplate {
	
	@Test
	public void test(){
		AbstractTemplate mother = new Mother();
		mother.prepareGotoSchool();
		
		AbstractTemplate son = new Son();
		son.prepareGotoSchool();
		
	}

}
