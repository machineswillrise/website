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
			<li>I use FOSS software for nearly everything. I hate face recognition and mass surveillance.</li>
			<li>I like Raspberry Pi and ESP32 development and experimenting with interesting hardware.
		</ul>
	</div>

	<h2>Favorites</h2>
	<div class="card">
		<ul>
			<li>Song: Icon of Coil - Shelter</li>
			<li>Animal: Cat</li>
			<li>Language: Java</li>
			<li>Indentation: Tabs :)</li>
		</ul>
	</div>

	<center>
		<p>Here is a picture of my beautiful cat:</p>
		<img src="/static/images/cat.jpg" alt="My Cat" class="cat">
		<p style="margin-bottom: 2rem;">yes... i own a blahaj</p>
	</center>

	<h2>Interesting Websites</h2>
	<div class="card">
		<p>These are some interesting and obscure websites I found.</p>
		<a href="https://eldritchdata.neocities.org">Eldritch Data</a>
		<a href="https://digdeeper.neocities.org">Dig Deeper</a>
		<a href="https://web.archive.org/web/20260311143609/http://www.tastyfish.cz/lrs/main.html">Drummyfish Wiki</a>
	</div>

	<p>You can contact me here or email me directly using the email on my GitHub page. Please only send me emails if something is important.</p>
	<form action="/contact" method="post" class="contact-form">
		<input type="text" name="name" placeholder="Name" required>
		<input type="email" name="email" placeholder="Email" required>
		<input type="text" name="message" placeholder="Message" required>
		<button type="submit">Send</button>
	</form>

	<!-- Web 2.0 Badges -->
	<img src="/static/images/no_spam.gif" alt="No Spam" class="badge">
	<a href="/donate">
		<img src="/static/images/donate.png" alt="Donate" class="badge">
	</a>
	<img src="/static/images/get_firefox.gif" alt="Get Firefox" class="badge">

	<!--For Marginalia and Wiby Search Engines -->
	<p class="invisible-text">
		Buzzwords to fulfill "Web 2.0" criteria: Less is more! Ruby on rails rocks! This is a public beta and start-up start up! Dave Legg is cool. AJAX is cool! http://isometric.sixsided.org/ http://www.slashdot.org/ Web 2.0 Web 2.0 Blogline blogroll foo mesh-up http://web2.0validator.com/ FireFox rocks! http://roker.dingens.web2.0php?foo=bar Nitro The Long Tail k2 share alike What is podcast podcasting Semantic Web blog foo.rdf
		Enlighten the FBI, CIA, Mossad et al: Bombe bomb George W. Bush G8 Heiligendamm Demonstration Attentat auf Schäuble Sprengstoff Dschihad jihad islam terror Allah Osama bin Laden Usama ibn Ladin Afghani Afghan Irak Iraq Krieg war anal sex KIPo teenager little girl vagina no its child by penis drogen drugs Stoff LSD THC mfg nuclear dirty bomb uran plutonium sarin U-Bahn Feuerlöscher Nägel Überwachung Vermummung Kaputze Schläger Klappmesser butterfly knife niger KKK Juden Nazi Hitler vergasen Auschwitz KZ Rauhkopierer Verbrecher illegal hacken cracken knacken sub zero day exploit xpl0itz Microsoft Windows Vista CSS decss DVD porn pr0n warez mp3z AllOfMP3.com TOR crypto break chipper AES DES RSA DNS LART BOFH w00t L.M.A.A. Fnord and line noise
	</p>
</@base>
