package com.browner.lambda.demo;

import java.nio.charset.StandardCharsets;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambdaAsyncClient;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.google.gson.Gson;

/**
 * @author Ronan Browne
 *
 *         Programmatically call our lambda function in the cloud from this
 *         class using a user set up for this purpose
 */
public class LamdbaCaller {

	public static void main(String[] args) {

		Gson json = new Gson();
		String name = "Ronan Browne from PC";
		String region = "eu-west-1";

		AWSLambdaAsyncClient client = new AWSLambdaAsyncClient(new ProfileCredentialsProvider("helloLamdbaUser"))
				.withRegion(Regions.fromName(region));
		HelloInput input = new HelloInput();
		input.setName(name);
		InvokeRequest request = new InvokeRequest().withFunctionName("MyFunction").withPayload(json.toJson(input));
		InvokeResult result = client.invoke(request);
		String s = StandardCharsets.UTF_8.decode(result.getPayload()).toString();
		HelloOutput output = json.fromJson(s, HelloOutput.class);
		
		System.out.println("Message: "+ output.getMessage());
		System.out.println("Function: "+ output.getFunctionName());
		System.out.println("Memory: "+ output.getMemoryLimit());

		
	}

}
