<#include "base.ftl">

<@base requestCount=requestCount>
	<h1>${post.title()}</h1>
	<p class="blog-date">${post.date()}</p>
	
	<div class="blog-content">
		<#list post.content()?split("\n") as paragraph>
			<#if paragraph?trim?has_content>
				<p>${paragraph}</p>
			</#if>
		</#list>
	</div>
	
	<a href="/blog" class="back-link">← Back to blog</a>
</@base>
