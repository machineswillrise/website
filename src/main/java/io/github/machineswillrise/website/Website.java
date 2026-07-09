package io.github.machineswillrise.website;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import com.sun.net.httpserver.HttpServer;

import io.github.machineswillrise.website.routing.ClasspathStaticHandler;
import io.github.machineswillrise.website.routing.Dispatcher;
import io.github.machineswillrise.website.service.BlogService;
import io.github.machineswillrise.website.service.NtfyService;

public class Website {
	private static final Logger LOG = Logger.getLogger(Website.class.getName());

	private static void configureRoutes(Dispatcher dispatcher, NtfyService ntfy) {
		dispatcher.register("GET", "/",             ctx -> ctx.renderTemplate("index.ftl"));
		dispatcher.register("GET", "/donate",       ctx -> ctx.renderTemplate("donate.ftl"));
		dispatcher.register("GET", "/contact-misc", ctx -> ctx.renderTemplate("contact-misc.ftl"));
		dispatcher.register("GET", "/health",       ctx -> ctx.respond(200, "OK"));

		dispatcher.register("POST", "/contact", ctx -> {
			var name = ctx.getBodyParam("name", "");
			var email = ctx.getBodyParam("email", "");
			var message = ctx.getBodyParam("message", "");

			var ntfyMessage = String.format("Contact form submission from %s (%s): %s", name, email, message);
			ntfy.sendNotification(ntfyMessage);

			ctx.renderTemplate("success.ftl");
		});

		// the attackers will enjoy this
		var wordpressUrls = List.of("/wp-admin", "/wp-login.php", "/wp-signup.php", "/wp-register.php");
		for (var url : wordpressUrls) {
			dispatcher.register("GET", url, ctx -> {
				ctx.respond(200, "Fuck you, I hope your mother dies in her sleep");
			});
		}
	}

	private static void configureBlogRoutes(Dispatcher dispatcher, BlogService blog) {
		dispatcher.register("GET", "/blog", ctx -> {
			var articles = new HashMap<String, Object>();
			articles.put("posts", blog.getAllPosts());
			ctx.renderTemplate("blog.ftl", articles);
		});

		dispatcher.register("GET", "/blog/{slug}", ctx -> {
			var slug = ctx.getPathParam("slug");
			var post = blog.getPostBySlug(slug);
			if (post == null) {
				ctx.renderTemplate("404.ftl");
				return;
			}

			var postMap = new HashMap<String, Object>();
			postMap.put("post", post);
			ctx.renderTemplate("blog-post.ftl", postMap);
		});
	}

	public static void main(String[] args) throws IOException {
		var context = new Context();
		var dispatcher = context.dispatcher;
		var ntfyService = context.ntfyService;
		var blogService = new BlogService();

		configureRoutes(dispatcher, ntfyService);
		configureBlogRoutes(dispatcher, blogService);

		var port = new InetSocketAddress(context.PORT);
		var server = HttpServer.create(port, 0);
		server.setExecutor(Executors.newCachedThreadPool());

		var staticHandler = new ClasspathStaticHandler("static/");
		server.createContext("/", dispatcher);
		server.createContext("/static", staticHandler.create(dispatcher));

		server.start();

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			LOG.info("Shutting down...");
			dispatcher.shutdown();
			server.stop(0);
		}));

		LOG.info(() -> "Server started on port " + context.PORT);
	}
}
