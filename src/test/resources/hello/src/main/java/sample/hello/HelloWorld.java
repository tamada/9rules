package sample.hello;

/**
 * Hello world!
 *
 */
public class HelloWorld{
    private String defaultName;

    public void setDefaultName(String defaultName){
        this.defaultName = defaultName;
    }

    public String getDefaultName(){
        return defaultName;
    }

    public String hello(String name){
        StringBuilder sb = new StringBuilder("Hello, ");
        sb.append(name);
        sb.append("!");
        return new String(sb);
    }

    public String hello(){
        return hello("World");
    }
}
