package Afg2RuntimeEnviornment;

public class NewMain {
    public static void main(String[] args) {
        String pathToJar = System.getProperty("user.home") + "/IdeaProjects/OOKASS24/out/artifacts/OOKAAbgabeLukasLeschUeb1_jar/OOKAAbgabeLukasLeschUeb1.jar";

        RunEnvironment rv = new RunEnvironment();
        rv.saveConfigLocally.emptyConfig();
        rv.startEnviornment();
        rv.loadConfig();

        rv.deployComponent(pathToJar,"Thread1");
        rv.deployComponent(pathToJar,"Thread2");
        rv.deployComponent(pathToJar,"Thread3");
        rv.getStatus();
        rv.startComponent(1);
        rv.startComponent(2);
        rv.startComponent(3);

        rv.getStatus();

        rv.injectLoggerIntoComponent(1);
        rv.injectLoggerIntoComponent(2);
        rv.injectLoggerIntoComponent(3);

        rv.sendLogger(1);
        rv.sendLogger(2);
        rv.sendLogger(3);

        rv.runComponentMethod(pathToJar,"searchHotelbyName",new Object[]{"Riu Plaza"});
        rv.runComponentMethod(pathToJar,"searchHotelbyName",new Object[]{"Riu Plaza"});
        rv.runComponentMethod(pathToJar,"searchHotelbyName",new Object[]{"Riu Plaza"});
        rv.runComponentMethod(pathToJar,"searchHotelbyName",new Object[]{"Riu Plaza"});
        rv.runComponentMethod(pathToJar,"searchHotelbyName",new Object[]{"Riu Plaza"});
        rv.runComponentMethod(pathToJar,"searchHotelbyName",new Object[]{"Riu Plaza"});
        rv.runComponentMethod(pathToJar,"searchHotelbyName",new Object[]{"Riu Plaza"});
        rv.runComponentMethod(pathToJar,"searchHotelbyName",new Object[]{"WinzerHotel"});
        rv.runComponentMethod(pathToJar,"searchHotelbyName",new Object[]{"Riu Plaza"});
        rv.runComponentMethod(pathToJar,"searchHotelbyName",new Object[]{"Riu Plaza"});
        rv.runComponentMethod(pathToJar,"searchHotelbyName",new Object[]{"ElsHotel"});
        rv.runComponentMethod(pathToJar,"searchHotelbyName",new Object[]{"Riu Plaza"});
        rv.runComponentMethod(pathToJar,"searchHotelbyName",new Object[]{"Riu Plaza"});

        rv.stopComponent(1);
        rv.stopComponent(2);
        rv.stopComponent(3);




      rv.unDeployComponent(1);
        rv.unDeployComponent(2);
        rv.unDeployComponent(3);


       rv.getStatus();

        rv.stopEnviornment();
    }
}
