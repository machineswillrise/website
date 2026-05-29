package io.github.machineswillrise.website.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import java.util.logging.Logger;

public class BlogService {
	private static final Logger LOG = Logger.getLogger(BlogService.class.getName());

	private static final Pattern METADATA_PATTERN = Pattern.compile("^---\\s*$");
	private static final Pattern KEY_VALUE_PATTERN = Pattern.compile("^([a-zA-Z_]+):\\s*(.*)$");
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public record BlogPost(String slug, String title, LocalDate date, String content, String excerpt) {}
	private final List<BlogPost> posts;

	public BlogService() {
		this.posts = new ArrayList<>();
		loadPosts();
	}

	private void loadPosts() {
		List<String> postFiles = readLinesFromResource("blog/posts.txt");
		if (postFiles == null) {
			LOG.warning(() -> "posts.txt not found in resources/blog/");
			return;
		}

		for (var postFile : postFiles) {
			if (postFile == null || postFile.isBlank()) continue;
			var resourcePath = "blog/" + postFile.trim();
			try (var is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
				if (is != null) {
					var content = readAllFromStream(is);
					var post = parseMarkdownPost(resourcePath, content);
					if (post != null) {
						posts.add(post);
					}
				} else {
					LOG.warning(() -> "Resource not found: " + resourcePath);
				}
			} catch (IOException e) {
				LOG.warning(() -> "Failed to load blog post: " + resourcePath + " - " + e.getMessage());
			}
		}
	}

	private List<String> readLinesFromResource(String resourcePath) {
		try (var is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
			if (is == null) {
				return null;
			}
			try (var reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
				var lines = new ArrayList<String>();
				String line;
				while ((line = reader.readLine()) != null) {
					lines.add(line);
				}
				return lines;
			}
		} catch (IOException e) {
			LOG.warning(() -> "Error reading resource " + resourcePath + ": " + e.getMessage());
			return null;
		}
	}

	private String readAllFromStream(InputStream is) throws IOException {
		try (var reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
			var sb = new StringBuilder();
			String line;
			boolean first = true;
			while ((line = reader.readLine()) != null) {
				if (!first) {
					sb.append("\n");
				}
				sb.append(line);
				first = false;
			}
			return sb.toString();
		}
	}

	private BlogPost parseMarkdownPost(String filename, String content) {
		var lines = content.split("\n");
		var inMetadata = false;
		var title = "";
		var dateStr = "";
		var bodyLines = new ArrayList<String>();

		for (var line : lines) {
			if (METADATA_PATTERN.matcher(line).matches()) {
				inMetadata = !inMetadata;
				continue;
			}

			if (inMetadata) {
				var matcher = KEY_VALUE_PATTERN.matcher(line);
				if (matcher.matches()) {
					var key = matcher.group(1);
					var value = matcher.group(2);
					if (key.equals("title")) {
						title = value;
					} else if (key.equals("date")) {
						dateStr = value;
					}
				}
			} else {
				bodyLines.add(line);
			}
		}

		if (title.isEmpty() || dateStr.isEmpty()) {
			return null;
		}

		var slug = filename.substring(filename.lastIndexOf('/') + 1).replace(".md", "");
		var date = LocalDate.parse(dateStr, DATE_FORMATTER);
		var body = String.join("\n", bodyLines);
		var excerpt = body.length() > 200 ? body.substring(0, 200) + "..." : body;

		return new BlogPost(slug, title, date, body, excerpt);
	}

	public List<BlogPost> getAllPosts() {
		return new ArrayList<>(posts);
	}

	public BlogPost getPostBySlug(String slug) {
		return posts.stream()
			.filter(p -> p.slug.equals(slug))
			.findFirst()
			.orElse(null);
	}
}
