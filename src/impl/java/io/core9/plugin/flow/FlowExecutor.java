package io.core9.plugin.flow;


import java.util.concurrent.*;

public class FlowExecutor {
	private static final int THREAD_NUM = 1;
	private static AbstractFlowContext context;
	private static AbstractFlowStateHandler stateHandlers;

	public void doRun(AbstractFlowContext ctx) throws InterruptedException {
		context = ctx;
		ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_NUM);
		
		Node n = new Node(threadPool);
		threadPool.submit(n);
	}


	static class Node implements Runnable {

		ExecutorService executor;

		Node(ExecutorService executor) {
			this.executor = executor;
			stateHandlers.run();
		}

		@Override
		public void run() {
			stateHandlers.start(context);
		}
	}

	static String getThreadName() {
		return Thread.currentThread().getName();
	}

	//FIXME I got a headache from this piece of shit code!!
	public static String execute(AbstractFlowContext context) {
		stateHandlers = context.getStateHandler();
		if (context.result == null) {
			context.result = "";
		}
		waitForFinal(1, context);
		int nr = 0;
		while (!context.result.equals("final") || nr == 20) {
			waitForFinal(100, context);
			nr++;
		}
		return (String) context.data;
	}

	private static void waitForFinal(int sleep, AbstractFlowContext context) {

		if (sleep > 0) {
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}

		try {
			new FlowExecutor().doRun(context);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
