package com.github.mauricioaniche.ck;

import org.eclipse.jdt.core.dom.SimpleName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.github.mauricioaniche.ck.metric.LCOM;


import static org.junit.jupiter.api.Assertions.assertThrows;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LCOMTest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/lcom");
	}
	
	@Test
	public void should_count_lcom() {
		CKClassResult a = report.get("lcom.TripStatusBean");
		Assertions.assertEquals(1415, a.getLcom());

		CKClassResult b = report.get("lcom.SimpleGetterAndSetter");
		Assertions.assertEquals(0, b.getLcom());

		CKClassResult c = report.get("lcom.SimpleGetterAndSetter2");
		Assertions.assertEquals(2, c.getLcom());

		CKClassResult d = report.get("lcom.TermsOfServiceController");
		Assertions.assertEquals(0, d.getLcom());

	}

	@Test
	public void should_test_declared_fields() throws Exception{
		ck.classLevelMetrics.call().forEach(metric -> {
			if(metric instanceof LCOM) {
				LCOM lcom = (LCOM) metric;
				SimpleName name = null;
				assertThrows(NullPointerException.class, () -> lcom.visit(name));
			}
		});
	}
	
	
}