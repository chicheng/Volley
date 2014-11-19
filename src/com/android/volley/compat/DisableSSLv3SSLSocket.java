package com.android.volley.compat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.SSLSocket;

public class DisableSSLv3SSLSocket extends DelegateSSLSocket {
	
	private final static String SSLv3Protocol = "SSLv3";

	public DisableSSLv3SSLSocket(SSLSocket delegate) {
		super(delegate);
	}

	@Override
	public void setEnabledProtocols(String[] protocols) {
		
		if ( protocols != null && protocols.length == 1 && protocols[0].equals(SSLv3Protocol) ) {
			
			List<String> enabledProtocols = new ArrayList<>(Arrays.asList(delegate.getEnabledProtocols()));
			
			if ( enabledProtocols.size() > 1 ) {
				enabledProtocols.remove(SSLv3Protocol);
			}
			
			protocols = enabledProtocols.toArray(new String[enabledProtocols.size()]);
		}
		
		super.setEnabledProtocols(protocols);
	}
}
