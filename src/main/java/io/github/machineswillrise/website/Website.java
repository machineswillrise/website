package io.github.machineswillrise.website;

import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.net.InetSocketAddress;
import java.io.IOException;

import com.sun.net.httpserver.HttpServer;

import io.github.machineswillrise.website.context.AppContext;

public class Website {
	private static final Logger LOG = Logger.getLogger(Website.class.getName());

	private static void configureLogging() {
		var logger = Logger.getLogger("");

		var stdoutHandler = new StreamHandler(System.out, new SimpleFormatter()) {
			@Override
			public synchronized void publish(LogRecord record) {
				super.publish(record);
				flush();
			}
		};

		stdoutHandler.setLevel(Level.INFO);
		logger.setLevel(Level.INFO);

		logger.setUseParentHandlers(false);
		logger.addHandler(stdoutHandler);
	}

	public static void main(String[] args) throws IOException {
		configureLogging();

		var context = new AppContext();
		var dispatcher = context.dispatcher;

		dispatcher.register("/health", ctx -> ctx.respond(200, "OK"));

		var port = new InetSocketAddress(context.PORT);
		var server = HttpServer.create(port, 0);
		
		server.setExecutor(Executors.newCachedThreadPool());
		server.createContext("/", dispatcher);
		server.start();
	}
}
