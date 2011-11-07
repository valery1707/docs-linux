import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MegalineHosts {
    public static void main(String[] args) throws IOException {
        MegalineHosts runner = new MegalineHosts();
        runner.run(args);
    }

    private File input = null;
    private File output = null;

    private void run(String[] args) throws IOException {
        parseArgs(args);
        if (!hasInput()) {
            showHelp();
            return;
        }

        int itemCount;
        try {
            openOutput();
            itemCount = processInput();
        } finally {
            closeOutput();
        }
        System.out.println(String.format("Complited %d hosts", itemCount));
    }

    private void showHelp() {
        System.out.println("Usage: MegalineHosts file_with_hosts_list out_put_file");
    }

    private PrintStream out = null;

    private void openOutput() throws IOException {
        if (hasOutput()) {
            out = new PrintStream(getOutput());
        } else {
            out = System.out;
        }
    }

    private void closeOutput() {
        if (out != null && !out.equals(System.out)) {
            out.close();
        }
    }

    private int processInput() throws IOException {
        int result = 0;
        BufferedReader in = new BufferedReader(new FileReader(getInput()));
        String line;
        while ((line = in.readLine()) != null) {
            line = line.trim();
            if (line.startsWith("#") || line.isEmpty()) {
                writeComment(line);
            } else {
                result++;
                String host;
                if (line.contains(" ")) {
                    host = line.split(" ")[0];
                } else {
                    host = line;
                }
                try {
                    System.out.println("... Processing\t" + host);
                    for (InetAddress address : InetAddress.getAllByName(host)) {
                        if (address instanceof Inet4Address) {
                            writeAddress(host, address.getHostAddress());
                            break;
                        }
                    }
                } catch (UnknownHostException e) {
                    System.out.println("Invalid host: " + host);
                }
            }
        }
        return result;
    }

    private void writeComment(String line) {
        if (out != null) {
            out.println(line);
        }
    }

    private void writeAddress(String hostName, String hostAddress) {
        if (out != null) {
            out.format("%s\t%s\n", hostAddress, hostName);
        }
    }

    private void parseArgs(String[] args) {
        for (int i = 0, argsLength = args.length; i < argsLength; i++) {
            String arg = args[i];
            if (arg.startsWith("-") || arg.startsWith("/")) {
                String paramName = arg.substring(1);
                boolean hasParamValue = argsLength > (i + 1) && !args[i + 1].startsWith("-");
                if (paramName.equalsIgnoreCase("?") || paramName.equalsIgnoreCase("h")) {
                    //showHelp будет вызван при выходе так как не указан входной файл
                } else if (paramName.equalsIgnoreCase("out") && hasParamValue) {
                    setOutput(new File(args[i + 1]));
                }
            } else if (!hasInput()) {
                setInput(new File(arg));
            } else if (!hasOutput()) {
                setOutput(new File(arg));
            }
        }
    }

    public File getInput() {
        return input;
    }

    public void setInput(File file) {
        if (file.exists() && file.isFile() && file.canRead()) {
            this.input = file;
        } else {
            System.out.println("Incorrect input file: " + file.getAbsolutePath());
        }
    }

    public boolean hasInput() {
        return getInput() != null;
    }

    public File getOutput() {
        return output;
    }

    public void setOutput(File file) {
        File dir = file.getAbsoluteFile().getParentFile();
        if (file.exists() && file.isFile() && file.canWrite()) {
            this.output = file;
        } else if (dir != null && dir.exists() && dir.isDirectory() && dir.canWrite() 
                && (!file.exists())) {
            this.output = file;
        } else {
            System.out.println("Incorrect output file: " + file.getAbsolutePath());
        }
    }

    public boolean hasOutput() {
        return getOutput() != null;
    }
}