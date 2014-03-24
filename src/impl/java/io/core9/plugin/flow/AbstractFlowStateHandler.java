package io.core9.plugin.flow;

import au.com.ds.ef.EasyFlow;
import au.com.ds.ef.StatefulContext;

public class AbstractFlowStateHandler {

	public EasyFlow<StatefulContext> flow;
	public static String result;
	public static Object data;
	
	public void run() {
    }


	public void start(AbstractFlowContext ctx) {
		flow.start(ctx);
		ctx.awaitTermination();
		result = ctx.getResult();
		data = ctx.getData();
	}
}
