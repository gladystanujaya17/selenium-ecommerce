package test.Cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = {"src/test/java/features/pop.up.feature"},
		dryRun = !true,
		glue = "steps",
		snippets = SnippetType.CAMELCASE,
		monochrome = true
		)

public class Runner extends AbstractTestNGCucumberTests {

}
