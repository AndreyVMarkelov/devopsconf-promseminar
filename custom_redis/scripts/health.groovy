def socket = new Socket()
try {
    socket.connect(new InetSocketAddress('localhost', 6379), 5000)
    socket.withStreams { inputStream, outputStream ->
        outputStream.write("*1\r\n\$4\r\nPING\r\n".getBytes())
        outputStream.flush()
        def line = inputStream.newReader().readLine()
        if (line.contains("PONG")) {
            return true
        }
    }
} catch (Exception ex) {
    throw new RuntimeException("Error connect to redis", ex)
} finally {
    socket.close()
}