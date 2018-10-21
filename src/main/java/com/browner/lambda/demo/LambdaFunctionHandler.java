package com.browner.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * @author ronan browne
 * 
 * Practice application for learning AWS lamdba
 *
 */
public class LambdaFunctionHandler implements RequestHandler<HelloInput, HelloOutput> {

	@Override
	public HelloOutput handleRequest(HelloInput input, Context context) {
		HelloOutput myOutput = setOutput(input, context);
		context.getLogger().log("Hi...Input: " + input.getName());
		return myOutput;
	}

	/**
	 * Helper to set up Output for Lambda Function
	 * 
	 * @param input
	 * @param context
	 * @return Our custom output to return
	 */
	public HelloOutput setOutput(HelloInput input, Context context) {
		HelloOutput myOutput = new HelloOutput();
		myOutput.setMessage("Hello " + input.getName());
		myOutput.setFunctionName(context.getFunctionName());
		myOutput.setMemoryLimit(context.getMemoryLimitInMB());
		return myOutput;
	}

}
