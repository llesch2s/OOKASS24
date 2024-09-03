package Afg2RuntimeEnviornment;

public class QueueObject {
    private String methodName;
    private Object[] parameters;
    public QueueObject(String methodName, Object[] parameters) {
        if(parameters == null){
            parameters = new Object[0];
        }
        this.methodName = methodName;
        this.parameters = parameters;
    }
    public String getMethodName() {
        return methodName;
    }
    public Object[] getParameters() {
        return parameters;
    }
}
