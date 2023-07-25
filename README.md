<div align="center">
	<img src=".assets/tntreporter-logo.png" alt="tntreporter logo" height="400">

[![git-version](https://img.shields.io/badge/git%20version-Beta%200.1-green)]()
[![java](https://img.shields.io/static/v1?label=Made%20with&message=Java&logo=java&labelColor=&color=orange)](https://www.python.org)
[![license](https://img.shields.io/badge/License-MIT-yellow)](https://github.com/TrollSkull/TNT-Reporter/blob/main/LICENSE)
[![modrinth](https://img.shields.io/modrinth/v/:YyoKfcxQ)](https://modrinth.com/mod/tntreporter)
</div>

# 🧨 TNT Reporter
Lightweight plugin that detects and notify if the player has `broken/placed/collected or activated` TNT blocks.

Also this plugin can block active TNT blocks and prevent the explosion with a silly rocket explosion, check [Features](#-features) for more info!

### 🔗 Table of content.
- [Features](#-features)
    - [Prevent and moderate Griefing](#-prevent-and-moderate-griefing)
    - [Lightweight and Friendly](#-very-lightweight-and-friendly-with-low-resource-servers)
    - [Highly ustomizable](#-highly-customizable)
    - [Modular](#-modular)
- [Screenshots](#-screenshots--media)
- [Compiling](#-compiling)
- [Contributing](#-how-can-i-contribute)
- [License](#-license)

## ✨ Features
- ### 🔥 Prevent and moderate griefing.
     Deny the use of TNT and its explosion with this plugin, it also detects the users who placed and destroyed TNT.
  
- ### ⚡ Very lightweight and friendly with low-resource servers.
     The plugin has a clean and lightweight code `(16kb)` which uses few server resources.

- ### 🔩 Highly customizable.
     You can create or download translation files for this plugin, check translation folders and [Translations](#-translations) for more info.
  
     `(Disabled for now, stay tuned!)`
     ~Choose in which dimensions and worlds you want the plugin to fulfill its function~, also you can enable and disable those features whenever you want!

- ### 📚 Modular.
     Code easy to maintain and update, each function/feature has its own class separated by files (Modules), making it possible to include these modules in other plugins.

## 📖 Commands and permissions
| Command              | Description            | Permission         |
| -------------------- | ---------------------- | ------------------ |
| /tntreporter help    | Display a help menu    | tntreporter.help   |
| /tntreporter reload  | Reload plugin          | tntreporter.reload |
| /tntreporter report  | Show players TNT stats | tntreporter.report |

## 📸 Screenshots & Media
<img src="/.assets/demo.gif" width="720" height="448"/>

## 📦 Compiling
First you need to download some requirements such as [Maven](https://maven.apache.org/) and [Java SE 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).

I usually use [Visual Studio Code](https://code.visualstudio.com/), therefore it will be the IDE in which we will be compiling with Maven.

Move the extracted Maven folder where you want, then inside the folder there will be a folder called bin, copy the route and add it to the path.

Then open the project folder (TNTReporter), which inside has the `/src` folder and the `pom.xml` file.
In the lower left corner you should see a drop-down menu called "Maven", in that menu our project will appear.

<img src="/.assets/compile-help.png" width="620" height="388"/>

Then you simply have to `Right Click > Run Maven Commands > Install`.

Once the project has been compiled a folder called `/target` should have been created, inside that folder you will find the compiled plugin (tntreporter.jar).

## 🔧 How can I contribute?
Please read **[CONTRIBUTING](https://github.com/TrollSkull/TNTReporter/blob/main/CONTRIBUTING.md)** and thank you for the help!

## 📝 License
**MIT License © TNT Reporter**, see the full license at **[LICENSE](https://github.com/TrollSkull/TNT-Reporter/blob/main/LICENSE)**.
