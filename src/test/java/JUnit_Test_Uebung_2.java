import Afg2RuntimeEnviornment.RunEnvironment;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class JUnit_Test_Uebung_2 {
    @Test
    public void testStartEnvironment(){
        RunEnvironment testRV = new RunEnvironment();
        testRV.startEnviornment();
        assertEquals("LZU nicht gestartet!", "true", testRV.environmentStatus());
        testRV.stopEnviornment();
    }

    @Test
    public void testStopEnvironment(){
        RunEnvironment testRV = new RunEnvironment();
        testRV.startEnviornment();
        testRV.stopEnviornment();
        assertEquals("LZU nicht gestoppt!", "false", testRV.environmentStatus());
    }

    @Test
    public void testDeployComponent(){
        RunEnvironment testRV = new RunEnvironment();
        String testJar = System.getProperty("user.home") + "/IdeaProjects/OOKASS24/out/artifacts/OOKAAbgabeLukasLeschUeb1_jar/OOKAAbgabeLukasLeschUeb1.jar";
        String[] testComponents = { "1 - Test1 - notRunning" };
        testRV.startEnviornment();
        testRV.deployComponent(testJar,"Test1");
        assertArrayEquals("Nicht alle Komponenten deployed!", testComponents, testRV.getStatus());
        testRV.stopEnviornment();
    }

    @Test
    public void testUnDeployComponent(){
        RunEnvironment testRV = new RunEnvironment();
        String testJar = System.getProperty("user.home") + "/IdeaProjects/OOKASS24/out/artifacts/OOKAAbgabeLukasLeschUeb1_jar/OOKAAbgabeLukasLeschUeb1.jar";
        String[] testComponents = { };
        testRV.startEnviornment();
        testRV.deployComponent(testJar,"Test1");
        testRV.unDeployComponent(1);
        assertArrayEquals("Nicht alle Komponenten undeployed!", testComponents, testRV.getStatus());
        testRV.stopEnviornment();
    }

    @Test
    public void testStartComponent(){
        RunEnvironment testRV = new RunEnvironment();
        String testJar = System.getProperty("user.home") + "/IdeaProjects/OOKASS24/out/artifacts/OOKAAbgabeLukasLeschUeb1_jar/OOKAAbgabeLukasLeschUeb1.jar";
        String[] testComponents = { "1 - Test1 - Running" };
        testRV.startEnviornment();
        testRV.deployComponent(testJar,"Test1");
        testRV.startComponent(1);
        assertArrayEquals("Nicht alle Komponenten gestartet!", testComponents, testRV.getStatus());
        testRV.stopEnviornment();
    }

    @Test
    public void testStopComponent(){
        RunEnvironment testRV = new RunEnvironment();
        String testJar = System.getProperty("user.home") + "/IdeaProjects/OOKASS24/out/artifacts/OOKAAbgabeLukasLeschUeb1_jar/OOKAAbgabeLukasLeschUeb1.jar";
        String[] testComponents = { "1 - Test1 - notRunning" };
        testRV.startEnviornment();
        testRV.deployComponent(testJar,"Test1");
        testRV.startComponent(1);
        testRV.stopComponent(1);
        assertArrayEquals("Nicht alle Komponenten gestoppt!", testComponents, testRV.getStatus());
        testRV.stopEnviornment();
    }

    @Test
    public void testIsolatedComponents(){
        RunEnvironment testRV = new RunEnvironment();
        String testJar = System.getProperty("user.home") + "/IdeaProjects/OOKASS24/out/artifacts/OOKAAbgabeLukasLeschUeb1_jar/OOKAAbgabeLukasLeschUeb1.jar";
        String testArray;
        testRV.startEnviornment();

        testRV.deployComponent(testJar,"Test1");
        testRV.deployComponent(testJar,"Test2");
        testRV.deployComponent(testJar,"Test3");
        testRV.deployComponent(testJar,"Test4");
        testRV.deployComponent(testJar,"Test5");

        testRV.startComponent(1);
        testRV.startComponent(2);
        testRV.startComponent(3);
        testRV.startComponent(4);
        testRV.startComponent(5);

        testArray = Arrays.toString(testRV.getStatus());
        assertTrue("Nicht alle Komponenten gestartet!", testArray.contains("1 - Test1 - Running") && testArray.contains("2 - Test2 - Running") && testArray.contains("3 - Test3 - Running") && testArray.contains("4 - Test4 - Running") && testArray.contains("5 - Test5 - Running"));
        testRV.stopEnviornment();
    }
}
