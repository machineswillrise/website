<#include "base.ftl">

<@base requestCount=requestCount>
	<!-- Android Banner -->
	<script src="https://keepandroidopen.org/banner.js?size=mini&animation=off" integrity="sha256-/lB05PMa6MIAwXual6j7x5PdHoUUdEmv+nwThTIGUv0=" crossorigin="anonymous"></script>

	<h1>Machines Will Rise</h1>
	<p class="subtitle">Hi! I'm a 16 year old high school student that loves GNU/Linux and Emacs.</p>

	<h2>Links</h2>
	<div class="card">
		<a href="https://github.com/machineswillrise">GitHub</a>
		<a href="https://stackoverflow.com/users/32795609/machineswillrise">Stack Overflow</a>
		<a href="/blog">Blog</a>
	</div>

	<h2>Projects</h2>
	<div class="card">
		<a href="https://github.com/machineswillrise/jagent">JAgent</a>
		<a href="https://github.com/machineswillrise/javawm">JavaWM (Not complete yet)</a>
		<a href="https://github.com/machineswillrise/website">Website</a>
		<a href="https://github.com/machineswillrise/misc-school-stuff">Misc School Stuff</a>
		<a href="https://github.com/machineswillrise/dotfiles">Dotfiles</a>
	</div>

	<h2>Hobbies</h2>
	<div class="card">
		<ul>
			<li>I have 7 laptops, all running Linux. I currently use a Dell laptop running Debian with XFCE and XLibre. I like Arch too but I prefer not to daily drive it.</li>
			<li>I use FOSS software for nearly everything.</li>
			<li>I like Raspberry Pi and ESP32 development and experimenting with interesting hardware.</li>
		</ul>
	</div>

	<h2>Favorites</h2>
	<div class="card">
		<ul>
			<li>Song: Icon of Coil - Shelter</li>
			<li>Animal: Cat</li>
			<li>Language: Java</li>
			<li>OS: Debian</li>
			<li>Browser: LibreWolf</li>
			<li>Search Engine: Mojeek</li>
			<li>Indentation: Tabs :)</li>
		</ul>
	</div>

	<center>
		<p>Here is a picture of my beautiful cat:</p>
		<img src="/static/images/cat.jpg" alt="My Cat" class="cat">
		<p style="margin-bottom: 2rem">yes... i own a blahaj</p>
	</center>

	<h2>Interesting Websites</h2>
	<div class="card">
		<p>These are some interesting and obscure websites I found.</p>
		<div>
			<p style="margin-bottom: 1rem">Clearnet Sites:</p>
			<a href="https://eldritchdata.neocities.org">Eldritch Data</a>
			<a href="https://digdeeper.neocities.org">Dig Deeper</a>
			<a href="https://web.archive.org/web/20260311143609/http://www.tastyfish.cz/lrs/main.html">Drummyfish Wiki</a>
			<a href="http://warp.povusers.org/grrr/index.html">Warp</a>
		</div>

		<div>
			<p style="margin-bottom: 1rem">Onion Sites:</p>
			<a href="http://worldboxd6boiz3565vylaucsc7qqrbd6yflwhnlrwmbk5sydsfx4wyd.onion">World Box</a>
			<a href="http://nanochanrayhy7nuuhldw2n4sq7tmv7xzdwaxtbptetss5eaznwfknyd.onion">Nano Chan</a>
			<a href="http://wormgptkf23rl2jz53krs765bziz6any5waparuuc6rdz5az2yp7p7ad.onion">Worm GPT</a>
			<a href="http://cccpastefzuz6unowzgjtdwmxwjfgf5za7owwci3b2zys4jmc3xjszad.onion">Soviet Pastebin</a>
		</div>
	</div>

	<h2>Contact</h2>
	<form action="/contact" method="post" class="contact-form">
		<input type="text" name="name" placeholder="Name" required>
		<input type="email" name="email" placeholder="Email" required>
		<input type="text" name="message" placeholder="Message" required>
		<button type="submit">Send</button>
		<a href="/contact-misc">Other contact methods</a>
	</form>

	<!-- Web 2.0 Badges -->
	<img src="/static/images/no_spam.gif" alt="No Spam" class="badge">
	<a href="/donate">
		<img src="/static/images/donate.png" alt="Donate" class="badge">
	</a>
	<img src="/static/images/get_firefox.gif" alt="Get Firefox" class="badge">
	<img src="/static/images/pride.gif" alt="Pride" class="badge">
	<img src="/static/images/no_discord.gif" alt="No Discord" class="badge">
	<img src="/static/images/cookie_free.gif" alt="Cookie Free" class="badge">
</@base>
