<#include "base.ftl">

<@base requestCount=requestCount>
	<link rel="stylesheet" href="/static/blog.css">
	<h1>Blog</h1>
	<p class="subtitle">Thoughts, ideas, and ramblings</p>
	
	<div class="blog-posts">
		<#list posts as post>
			<div class="blog-post">
				<h2><a href="/blog/${post.slug()}">${post.title()}</a></h2>
				<p class="blog-date">${post.date()}</p>
				<p class="blog-excerpt">${post.excerpt()}</p>
				<a href="/blog/${post.slug()}" class="read-more">Read more</a>
			</div>
		</#list>
	</div>
</@base>
