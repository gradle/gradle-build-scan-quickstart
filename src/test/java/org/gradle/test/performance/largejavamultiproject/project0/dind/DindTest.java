package org.gradle.test.performance.largejavamultiproject.project0.dind;

import org.gradle.test.performance.largejavamultiproject.project0.util.Sleeper;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.String.format;
import static java.util.stream.IntStream.rangeClosed;

@Ignore("Test should only run when in a docker-in-docker environment")
public class DindTest {

    @SuppressWarnings("rawtypes")
    @Rule
    public GenericContainer greeter = new GenericContainer("cjimti/go-echo")
        .withEnv("TCP_PORT", "4242")
        .withEnv("NODE_NAME", "EchoServer")
            .withExposedPorts(4242);

    @Test
    public void dindTest() throws Exception {
        var host = greeter.getHost();
        var port = greeter.getFirstMappedPort();
        var url = format("http://%s:%d", host, port);
        System.out.println("Greeter available at " + url);

        try (TcpClient client = new TcpClient(host, port)) {
            rangeClosed(1, 10).forEach(idx ->
                {
                    try {
                        var message = randomText();
                        System.out.println("Sending: " + message);
                        var response = client.sendMessage(message);
                        System.out.println("Response: " + response);
                    } catch (Exception exc) {
                        throw new RuntimeException("Failure", exc);
                    }

                    System.out.println("Sleeping for iteration " + idx);
                    Sleeper.sleep(Duration.ofSeconds(5));
                }
            );
        }
    }

    private static String randomText() {
        var randomInt = ThreadLocalRandom.current().nextInt(100);
        return String.valueOf(randomInt);
    }


    static class TcpClient implements AutoCloseable {
        private final Socket clientSocket;
        private final PrintWriter out;
        private final BufferedReader in;

        TcpClient(String host, int port) throws Exception {
            clientSocket = new Socket(host, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }

        public String sendMessage(String msg) throws Exception {
            out.println(msg);
            return in.readLine();
        }

        @Override
        public void close() {
            failsafeClose(in, out, clientSocket);
        }

        private void failsafeClose(Closeable... resourcesToClose) {
            RuntimeException errorsWhileClosing = null;

            for (Closeable resource : resourcesToClose) {
                try {
                    resource.close();
                } catch (Throwable t) {
                    if (errorsWhileClosing == null) {
                        errorsWhileClosing = new RuntimeException("Closing resources failed", t);
                    } else {
                        errorsWhileClosing.addSuppressed(t);
                    }
                }
            }

            if (errorsWhileClosing != null) {
                throw errorsWhileClosing;
            }
        }
    }

}
