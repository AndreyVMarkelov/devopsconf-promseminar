import java.net.InetSocketAddress
import java.net.Socket

def socket = new Socket()
try {
    socket.connect(new InetSocketAddress('redis', 6379), 5000)
    socket.withStreams { inputStream, outputStream ->
        outputStream.write("*1\r\n\$4\r\nPING\r\n".getBytes())
        outputStream.flush()
        def line = inputStream.newReader().readLine()
        if (line.contains("PONG")) {
            return true
        }
    }
} catch (Throwable th) {
    throw new RuntimeException("Error connect to redis " + th.getMessage(), th)
} finally {
    socket.close()
}