<#include "base.ftl">

<@base requestCount=requestCount>
	<h1>Machines Will Rise</h1>
	<p class="subtitle">Welcome to my personal portfolio website!</p>
	<p>Feel free to explore my projects and contact me if you have any questions.</p>

	<h2>Links</h2>
	<div class="links">
		<a href="https://github.com/machineswillrise">GitHub</a>
		<a href="https://news.ycombinator.com/user?id=onesingleblast">Hacker News</a>
		<a href="/blog">Blog</a>
	</div>

	<h2>Projects</h2>
	<div class="projects">
		<a href="https://github.com/machineswillrise/java-encrypter">Java Encrypter</a>
		<a href="https://github.com/machineswillrise/java-restaurant">Java Restaurant</a>
		<a href="https://github.com/machineswillrise/jagent">JAgent</a>
		<a href="https://github.com/machineswillrise/javawm">JavaWM (Incomplete right now)</a>
		<a href="https://github.com/machineswillrise/rna-analyzer">RNA Analyzer</a>
		<a href="https://github.com/machineswillrise/java-tictactoe">Java Tic-Tac-Toe</a>
		<a href="https://github.com/machineswillrise/dotfiles">Dotfiles</a>
	</div>

	<form action="/contact" method="post" class="contact-form">
		<input type="text" name="name" placeholder="Name" required>
		<input type="email" name="email" placeholder="Email" required>
		<input type="text" name="message" placeholder="Message" required>
		<button type="submit">Send</button>
	</form>
</@base>
