package com.android.volley.compat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class DisableSSLv3SSLSocketFactory extends SSLSocketFactory {
	
	private final SSLSocketFactory delegate;
	
	public DisableSSLv3SSLSocketFactory() {
		this.delegate = HttpsURLConnection.getDefaultSSLSocketFactory();
	}
	
	private static Socket getSafeSocket(Socket socket) {
		
		if (socket instanceof SSLSocket) {
			socket = new DisableSSLv3SSLSocket((SSLSocket) socket);
		}
		
		return socket;
	}

	@Override
	public String[] getDefaultCipherSuites() {
		return delegate.getDefaultCipherSuites();
	}

	@Override
	public String[] getSupportedCipherSuites() {
		return delegate.getSupportedCipherSuites();
	}

	@Override
	public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
		return getSafeSocket(delegate.createSocket(s, host, port, autoClose));
	}

	@Override
	public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
		return getSafeSocket(delegate.createSocket(host, port));
	}

	@Override
	public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
		return getSafeSocket(delegate.createSocket(host, port, localHost, localPort));
	}

	@Override
	public Socket createSocket(InetAddress host, int port) throws IOException {
		return getSafeSocket(delegate.createSocket(host, port));
	}

	@Override
	public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
		return getSafeSocket(delegate.createSocket(address, port, localAddress, localPort));
	}

}
