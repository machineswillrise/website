<#include "base.ftl">

<@base requestCount=requestCount>
	<h1>Machines Will Rise</h1>
	<p class="subtitle">Hi! I'm a 16 year old high school student that loves GNU/Linux and Emacs.</p>

	<h2>Links</h2>
	<div class="links">
		<a href="https://github.com/machineswillrise">GitHub</a>
		<a href="https://stackoverflow.com/users/32795609/machineswillrise">Stack Overflow</a>
		<a href="/blog">Blog</a>
	</div>

	<h2>Projects</h2>
	<div class="projects">
		<a href="https://github.com/machineswillrise/jagent">JAgent</a>
		<a href="https://github.com/machineswillrise/javawm">JavaWM (Not complete yet)</a>
		<a href="https://github.com/machineswillrise/website">Website</a>
		<a href="https://github.com/machineswillrise/misc-school-stuff">Misc School Stuff</a>
		<a href="https://github.com/machineswillrise/dotfiles">Dotfiles</a>
	</div>

	<h2>Hobbies</h2>
	<div class="hobbies">
		<ul>
			<li>I have 7 laptops, all running Linux. I currently use a Dell laptop running Debian with XFCE and XLibre. I like Arch too but I prefer not to daily drive it.</li>
			<li>I use FOSS software for nearly everything. I hate face recognition and mass surveillance.</li>
			<li>I like Raspberry Pi and ESP32 development and experimenting with interesting hardware.
		</ul>
	</div>

	<h2>Favorites</h2>
	<div class="favorites">
		<ul>
			<li>Song: Icon of Coil - Shelter</li>
			<li>Animal: Cat</li>
			<li>Language: Java</li>
			<li>Indentation: Tabs :)</li>
		</ul>
	</div>

	<p>You can contact me here or email me directly. Please only send me emails if something is important.</p>
	<form action="/contact" method="post" class="contact-form">
		<input type="text" name="name" placeholder="Name" required>
		<input type="email" name="email" placeholder="Email" required>
		<input type="text" name="message" placeholder="Message" required>
		<button type="submit">Send</button>
	</form>
</@base>
