<#include "base.ftl">

<@base requestCount=requestCount>
	<!-- Android Banner -->
	<script src="https://keepandroidopen.org/banner.js?size=mini&animation=off" integrity="sha256-/xV4ZzeJ6QDnfxa1SmjeRcjJJCAuSeBPBaFryspoARk=" crossorigin="anonymous"></script>

	<h1>Machines Will Rise</h1>
	<p class="subtitle">Hi! I'm a 16 year old high school student that loves GNU/Linux and Emacs.</p>

	<h2>Links</h2>
	<div class="card">
		<a href="https://github.com/machineswillrise">GitHub</a>
		<a href="https://stackoverflow.com/users/32795609/machineswillrise">Stack Overflow</a>
	</div>

	<h2>Projects</h2>
	<div class="card">
		<a href="https://github.com/machineswillrise/jagent">JAgent</a>
		<a href="https://github.com/machineswillrise/fruit-game">Fruit Game</a>
		<a href="https://github.com/machineswillrise/website">Website</a>
		<a href="https://github.com/machineswillrise/misc-school-stuff">Misc School Stuff</a>
		<a href="https://github.com/machineswillrise/dotfiles">Dotfiles</a>
	</div>

	<h2>Hobbies</h2>
	<div class="card">
		<ul>
			<li>I have 7 laptops, all running Linux. I currently use a Dell laptop running Debian with XFCE and XLibre. I like Arch too but I prefer not to daily drive it.</li>
			<li>I use free software for nearly everything.</li>
			<li>I like Raspberry Pi and ESP32 development and experimenting with interesting hardware.</li>
		</ul>
	</div>

	<h2>Favorites</h2>
	<div class="card">
		<ul>
			<li>Animal: Cat</li>
			<li>Artist: Icon of Coil</li>
			<li>Color: Purple</li>
			<li>Language: Java</li>
			<li>OS: Debian</li>
			<li>Search Engine: Mojeek</li>
			<li>Indentation: Tabs :)</li>
		</ul>
	</div>

	<h2>Likes</h2>
	<div class="card justify-list">
		<ul>
			<li>C</li>
			<li>C++ (but no obscure features)</li>
			<li>Rust</li>
			<li>Raku</li>
			<li>Biking</li>
			<li>LineageOS</li>
			<li>Tor</li>
			<li>I2P</li>
			<li>Monero</li>
			<li>Piracy</li>
			<li>Soulseek</li>
			<li>Anna's Archive</li>
			<li>Electronic repair</li>
			<li>TUIs</li>
			<li>Doomscrolling wikipedia</li>
			<li>Catppuccin theme</li>
			<li>Alpine Linux</li>
			<li>Old computers</li>
			<li>SDL and LWJGL</li>
		</ul>
	</div>

	<h2>Dislikes</h2>
	<div class="card justify-list">
		<p>Dislike does <b>NOT</b> mean hate. I love dogs and you as well ❤️</p>
		<ul>
			<li>Dogs</li>
			<li>Apple</li>
			<li>Gun control</li>
			<li>Flock cameras</li>
			<li>Mainstream music</li>
			<li>Curved monitors</li>
			<li>Godot and Unity</li>
			<li>Being around people</li>
		</ul>
	</div>

	<center>
		<h2>My Fastfetch</h2>
		<img src="/static/images/index/fastfetch.png" alt="Debian" class="tiny-image">

		<h2>My Beautiful Cat (Firefox)</h2>
		<img src="/static/images/index/firefox.jpg" alt="Firefox" class="tiny-image">
		<p style="margin-bottom: 2rem">yes... i own a blahaj</p>
	</center>

	<h2>Interesting Websites</h2>
	<div class="card">
		<p>These are some interesting and obscure websites I found.</p>
		<div>
			<p style="margin-bottom: 1rem">Clearnet Sites:</p>
			<a href="https://eldritchdata.neocities.org">Eldritch Data</a>
			<a href="https://digdeeper.neocities.org">Dig Deeper</a>
			<a href="https://www.tastyfish.cz">Drummyfish</a>
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

	<p style="margin-bottom: 1rem">How DARE you be accessing this through a desktop browser instead of our bloated and proprietary app.</p>

	<!-- Web 2.0 Badges -->
	<img src="/static/images/index/badges/no_spam.gif" alt="No Spam" class="badge">
	<img src="/static/images/index/badges/get_firefox.gif" alt="Get Firefox" class="badge">
	<img src="/static/images/index/badges/cookie_free.gif" alt="Cookie Free" class="badge">
	<img src="/static/images/index/badges/best_viewed_with_eyes.gif" alt="Best Viewed with Eyes" class="badge">
</@base>
