package com.harsha.resource.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;


public class HttpLoggingInterceptor implements ClientHttpRequestInterceptor {

	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		this.logRequest(request,body);
		ClientHttpResponse response = execution.execute(request, body);
		this.logResponse(response);
		return response;
	}

	private void logRequest(HttpRequest request, byte[] body) throws UnsupportedEncodingException {
	//Add request in logger to get it print in logs
		request.getURI();
		request.getMethod();
		request.getHeaders();
		new String(body,"UTF-8");
		
	}
	
	
	private void logResponse(ClientHttpResponse response) throws IOException {
		// Add response in logger to get it print in logs
		response.getStatusCode();
		response.getStatusText();
		response.getHeaders();
		StreamUtils.copyToString(response.getBody(), Charset.defaultCharset());
	}


	

}
