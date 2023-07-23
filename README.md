# 🧨 TNT Reporter
Lightweight plugin that detects and notify if the player has `broken/placed/collected or activated` TNT blocks.

Also this plugin can block active TNT blocks and prevent the explosion, check [Features](#-features) for more info!

### 🧾 Table of content
- [Features](#-features)
    - [Lightweight and Friendly](#-very-lightweight-and-friendly-with-low-resource-servers)
    - [Highly ustomizable](#-highly-customizable)
    - [Modular](#-modular)
- [Screenshots](#-screenshots--media)
- [Compiling](#-compiling)
- [Contributing](#-how-can-i-contribute)
    - [Creating new features](#-creating-new-features)
    - [Translations](#-translations)
    - [Bugfix](#-bugfix)
    - [Fixing typos](#%EF%B8%8F-fixing-typos)
- [License](#-license)

## ✨ Features
- ### 🔥 Prevent and moderate griefing.
     Deny the use of TNT and its explosion with this plugin, it also detects the users who placed and destroyed TNT.

- ### ⚡ Very lightweight and friendly with low-resource servers.
     The plugin has a clean and lightweight code `( 9kb )` which uses few server resources.

- ### 🔩 Highly customizable.
     You can create or download translation files for this plugin, check translation folders and [Translations](#-translations) for more info.

- ### 📚 Modular.
     Code easy to maintain and update, each function/feature has its own class separated by files (Modules), making it possible to include these modules in other plugins.

## 📸 Screenshots & Media

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
Please read **[CONTRIBUTING](https://github.com/TrollSkull/MBmanager/blob/main/CONTRIBUTING.md)** and thank you for the help!

- ### 💡 Creating new features

- ### 🈸 Translations

- ### 🐛 Bugfix

- ### ✒️ Fixing typos

## 📝 License
**MIT License © TNT Reporter**, see the full license at **[LICENSE](https://github.com/TrollSkull/TNT-Reporter/blob/main/LICENSE)**.
