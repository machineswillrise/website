package io.github.machineswillrise.website.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BlogService {
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
		try {
			var blogDir = getClass().getClassLoader().getResource("blog");
			if (blogDir == null) {
				System.err.println("Blog directory not found in resources");
				return;
			}

			var blogPath = java.nio.file.Paths.get(blogDir.toURI());
			var postFiles = java.nio.file.Files.list(blogPath)
				.filter(path -> path.toString().endsWith(".md"))
				.map(java.nio.file.Path::getFileName)
				.map(java.nio.file.Path::toString)
				.toList();

			for (var postFile : postFiles) {
				var resourcePath = "blog/" + postFile;
				try (var is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
					if (is != null) {
						var content = new String(is.readAllBytes(), StandardCharsets.UTF_8);
						var post = parseMarkdownPost(resourcePath, content);
						if (post != null) {
							posts.add(post);
						}
					}
				} catch (IOException e) {
					System.err.println("Failed to load blog post: " + resourcePath);
				}
			}
	} catch (java.net.URISyntaxException | java.io.IOException e) {
		System.err.println("Failed to load blog posts: " + e.getMessage());
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
