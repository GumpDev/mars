<div align="center">
    <img width="250px" src="mars.png" />
    <h1>Mars</h1>
    <p>Create Minecraft plugins easier, like make a plugin on earth but in mars</p>
    
</div>

<br>

## Topics
- [🪐 What is Mars?](#whatismars)
- [🔧 Installation](#installation)
- [📝 Getting Started](#getting-started)
- [🤔 FAQ](#faq)
- [🙏 Thanks](#thanks)

<br>
<a id="whatismars"></a>

## 🪐 What is Mars?

Mars' goal is to standardize and facilitate the creation of minecraft plugin, for example you can create a simple inventory with all online players just by doing:
```java
Player p;
GUIPagination playersInv = Mars.inventory.CreateGUIPlayers("Player list title", playerSelect -> p.sendMessage(playerSelected.getName() + " selected");
playersInv.open(p);
```


<br>
<a id="installation"></a>

## 🔧 Installation

### Installation with maven
First, add Mars' repository into ```pom.xml```:
```xml
<repositories>
    ...
    <repository>
        <id>mars</id>
        <url>https://repo.gump.dev/snapshots/</url>
    </repository>
</repositories>
```

Then, add Mars' dependency too:
```xml
<dependencies>
    ...
    <dependency>
        <groupId>dev.gump</groupId>
        <artifactId>mars</artifactId>
        <version>1.0-SNAPSHOT</version>
        <scope>compile</scope>
    </dependency>
</dependencies>
```

<br>

### Installation with Gradle
First, add Mars' repository into ```build.gradle```:
```
repositories {
    ...
    mavenCentral()
    maven {
        name = 'mars'
        url = 'https://repo.gump.dev/snapshots/'
    }
}
```

Then, add Mars' dependency too:
```
    dependencies {
        ...
        compile 'dev.gump:mars:1.0-SNAPSHOT'
    }
```
(i don't know if this is right, i don't use Gradle :P)

<br>
<a id="getting-started"></a>

## 📝 Getting Started

To get started we need to start Mars and declare your intention with ```Mars.init(plugin)```, when you start Mars will register all intents events:
```java
public final class Test extends JavaPlugin{
    @Override
    public void onEnable() {
        //'this' need be your plugin main class
        //'Intent.ALL' active all modules
        Mars.init(this, Intent.ALL);
    }
}
```

List of all intents:

- ALL
- ARMOR_STAND
- CHAT
- ITEMS
- INVENTORY
- INTERACTIONS

(you can start Mars like this: ```Mars.init(this, Intent.CHAT, Intent.ITEMS)```)

If you wanna learn more [Click here to see documentation](https://github.com/GumpDev/mars/wiki) 

<br>
<a id="faq"></a>

## 🤔 FAQ

- **Why you created that?** *Cuz is so boring to create an Item with a custom name in Bukkit, it's more than 4 lines, and I'm lazy*
- **I Found a BUG!** *[Click here](https://github.com/GumpDev/mars/issues) and open an issue*
- **Can I help with the project?** *Sure! just send your PR :D*
- **Can I contact you?** *Yep, send email to contact@gump.dev*

<br>
<a id="thanks"></a>

## 🙏 Thanks
Thanks to [Dean Bassett](https://github.com/deanveloper) for [SkullCreator](https://github.com/deanveloper/SkullCreator)

and thanks to [PNGWing](https://www.pngwing.com/en/free-png-zutbh) for M from Mars' logo
