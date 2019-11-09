import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;

public class TCPManager {
	private Mediator mediator;
	private int port;
	private HashMap<SocketChannel, String>dataMapper;
	Selector selector;
	ServerSocket serverSocket;
	Socket socket;

	public TCPManager(Mediator mediator) {
		this.mediator = mediator;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			selector = Selector.open();
			ServerSocketChannel socketChannel = ServerSocketChannel.open();
			socketChannel.configureBlocking(false);
			socketChannel.socket().bind(new InetSocketAddress("localhost", port));
			socketChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while(true) {
			try {
				selector.select();
				
				Iterator<?> keys = selector.selectedKeys().iterator();
				
				while(keys.hasNext()) {
					SelectionKey key = (SelectionKey) keys.next();
					keys.remove();
					
					if(!key.isValid())
						continue;
					if(key.isAcceptable()) {
						this.accept(key);
					}
					else if(key.isReadable()) {
						this.response(key);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void accept(SelectionKey key) {
		ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
		SocketChannel channel;
		
		try	{
			channel = serverChannel.accept();
			channel.configureBlocking(false);
			Socket socket = channel.socket();
			SocketAddress remoteAddr = socket.getRemoteSocketAddress();
			dataMapper.put(channel, remoteAddr.toString());
			channel.register(this.selector, SelectionKey.OP_READ);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void response(SelectionKey key) {
		InputStream is;
		OutputStream os;
		byte[] bytes = null;
		String[] data;
		boolean result = false;
		
		try {
			is = socket.getInputStream();
			os = socket.getOutputStream();
			int count = is.read(bytes);
			data = new String(bytes, 0, count, "UTF-8").split("/");
			
			switch(data[0]) {
			case "CRD":
				result = mediator.handle("Card", data)
				.handle("DBMS", (Card)mediator.getData());
				break;
			case "PW":
				result = mediator.handle("Excel", data).getData() != null;
				break;
			case "VLT":
				result = mediator.handle("Volunteer", data)
				.handle("DBMS", (Volunteer)mediator.getData())
				.handle("Excel", (Volunteer)mediator.getData())
				.getData() != null;
				break;
			}
			bytes = Boolean.toString(result).getBytes();
			os.write(bytes);
			os.flush();
			is.close();
			os.close();
			socket.close();
			
		} catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
	}
}
