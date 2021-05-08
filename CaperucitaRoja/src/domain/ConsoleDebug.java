package domain;

public class ConsoleDebug {
    static private ConsoleDebug singleton = null;

    static private boolean isPruebaEscenario = false;
    static private boolean isDebugging = false;
    static private boolean showLogs = false;
    static private boolean showVerboseLogs = false;
    static private boolean staticWolf = false;


    private ConsoleDebug() {
    }

    static public ConsoleDebug get() {

        if (singleton == null) {
            singleton = new ConsoleDebug();
        }
        return singleton;
    }

    public boolean isPruebaEscenario() {
        return isPruebaEscenario;
    }

    public void setPruebaEscenario(boolean isPruebaEscenario) {
        ConsoleDebug.isPruebaEscenario = isPruebaEscenario;
    }

    public void setModeDebug() {
        isDebugging = true;
    }

    public boolean isDebugging(){
        return isDebugging;
    }

    public void showLogs() {
        showLogs = true;
    }

    public boolean isShowingLogs(){
        return showLogs;
    }

    public void showVerboseLogs() {
        showVerboseLogs = true;
    }

    public boolean isShowVerboseLogs(){
        return showVerboseLogs;
    }

    public boolean isStaticWolf(){
        return staticWolf;
    }

    public void staticWolf() {
        staticWolf = true;
    }
}