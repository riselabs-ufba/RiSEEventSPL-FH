package rise.splcc.derivation;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

public class AntExecutor {

	public static void derivate(){
		File buildFile = new File("Build_Complete_Product.xml");
		Project p = new Project();
		p.setUserProperty("ant.file", buildFile.getAbsolutePath());
		p.setProperty("java.home", "/Library/JAva/JavaVirtualMachines/jdk1.8.0_25.jdk");
		p.init();
		ProjectHelper helper = ProjectHelper.getProjectHelper();
		p.addReference("ant.projectHelper", helper);
		helper.parse(p, buildFile);
		p.executeTarget(p.getDefaultTarget());
	}
}
