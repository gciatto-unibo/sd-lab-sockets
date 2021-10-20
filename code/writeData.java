void writeData(Socket socket, byte[] data) {   
    try {
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(data, 0, data.length);
            outputStream.flush();
            // avoid closing the stream: 
            // this would prevent subsequent writes
        } catch (IOException e) {
            onWritingError(e);
        } 
    }
}

void onWritingError(IOException e) { /* handle e */ }
