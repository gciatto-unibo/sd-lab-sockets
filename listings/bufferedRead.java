static final int BUFFER_SIZE = 1024;

void bufferedRead(Socket socket) {
    // buffer for holding chunks of read data
    byte[] buffer = new byte[BUFFER_SIZE];
    
    try {
        // get the socket's input stream (with automatic closure)
        try(InputStream inputStream = socket.getInputStream()) {
            while (true) {
                // attempts to read at most BUFFER_SIZE bytes,
                // readBytes contains the amount of bytes actually read
                int readBytes = inputStream.read(buffer);
                if (readBytes < 0) { // if the stream is over...
                    onReadingOver();
                    return; // interrupts the reading
                } else { // otherwise, if some bytes have been read
                    onChunkRead(buffer, readBytes);
                }
            }
        } catch (IOException e) { 
            // may occurr because the connection is closed...
            onReadingError(e);
        } 
    }
}

void onChunkRead(byte[] buffer, int size) { /* handle size new bytes */ }

void onReadingOver() { /* handle no more data to read */ }

void onReadingError(IOException e) { /* handle e */ }
