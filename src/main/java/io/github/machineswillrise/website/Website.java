package io.github.machineswillrise.website;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import com.sun.net.httpserver.HttpServer;

import io.github.machineswillrise.website.routing.ClasspathStaticHandler;
import io.github.machineswillrise.website.routing.Dispatcher;
import io.github.machineswillrise.website.service.NtfyService;

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

	private static void configureRoutes(Dispatcher dispatcher, NtfyService ntfy) {
		dispatcher.register("GET","/", ctx -> ctx.renderTemplate("index.ftl", new HashMap<>()));
		dispatcher.register("GET","/health", ctx -> ctx.respond(200, "OK"));
		dispatcher.register("POST", "/contact", ctx -> {
			var name = ctx.getBodyParam("name", "");
			var email = ctx.getBodyParam("email", "");
			var message = ctx.getBodyParam("message", "");

			var ntfyMessage = String.format("Contact form submission from %s (%s): %s", name, email, message);
			ntfy.sendNotification(ntfyMessage);

			ctx.renderTemplate("success.ftl", new java.util.HashMap<>());
		});
		dispatcher.register("GET", "/wp-admin.php", ctx -> {
			ctx.respond(200, "Fuck you, I hope your mother dies in her sleep");
		});
	}

	public static void main(String[] args) throws IOException {
		configureLogging();

		var context = new Context();
		var dispatcher = context.dispatcher;
		var ntfyService = context.ntfyService;
		configureRoutes(dispatcher, ntfyService);

		var port = new InetSocketAddress(context.PORT);
		var server = HttpServer.create(port, 0);
		server.setExecutor(Executors.newCachedThreadPool());

		var staticHandler = new ClasspathStaticHandler("/style.css");
		server.createContext("/", dispatcher);
		server.createContext("/style.css", staticHandler.create(dispatcher));
		server.start();

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			LOG.info("Shutting down...");
			dispatcher.shutdown();
			server.stop(0);
		}));

		LOG.info(() -> "Server started on port " + context.PORT);
	}
}
